import { useEffect, useState } from "react";
import Table from "../../../components/Table";
import axios from "axios";
import { Outlet, useNavigate } from "react-router-dom";
import { BsInfoCircle } from "react-icons/bs"
import jwt from "jwt-decode";
import { useAuthHeader, useAuthUser} from "react-auth-kit";
import DateFormat from "../../../components/DateFormat"
import {
  Button,
  Dialog,
  DialogHeader,
  DialogBody,
  DialogFooter,
  Input,
  Spinner,
  Textarea,
  Alert,
  Typography
} from "@material-tailwind/react";
import { ImFacebook } from "react-icons/im";

const TABLE_HEAD = [{ name: "Nr zgłoszenia", key: "userProblemId" }, { name: "Data wysłania", key: "userProblemStartDate" }, { name: "Status zgłoszenia", key: "userProblemStatus" }, { name: "Szczegóły", key: null }];
// const TABLE_HEAD = [{name: "Nazwa",key: "name"},{name:"Typ urządzenia",key:"productType"}, {name:"Cena",key:"price"} ,{name:"Szczegóły",key:null} ];
export default function Reports() {
  const userCred = useAuthUser()
  const navigate = useNavigate()
  const [products, setProducts] = useState([])
  const [loading, setLoading] = useState(true)
  const token = useAuthHeader()
  
  const [showError, setShowError] = useState(false);


  //WYSYLANIE
  const [description, setDescription] = useState("")
  const [open, setOpen] = useState(false);
  async function handleSubmit() {

    const credentials = userCred().data
    if (description?.length < 20) {
      setShowError(true);
    }
    else {
      const user = jwt(token());
      const data = {
        email: user.sub,
        description: description
      }
      const config = {
        params: {
          email: data.sub,
        },
        auth : {
          username: credentials.email,
          password: credentials.password
        },
        headers:{
          "Content-Type": "application/json"
        },
        data:{}
      }
      console.log(description)
      const apiUrl = 'http://localhost:8080/upc/v1/user-role/save-user-problem';
      const response = await axios.post(apiUrl, data, config);
      console.log(response)
      if (response.status === 200) {
        const tab = JSON.parse(localStorage.getItem("notifications"));
        let newTab;
        const message = {message:"Pomyślnie wysłano zgłoszenie",type:"SUCCESS"}
        if (tab) {
          newTab = [...tab, message];
        } else {
          newTab = [message];
        }

        window.localStorage.setItem("notifications", JSON.stringify(newTab));
        window.dispatchEvent(new Event("storage"))
        window.location.reload();
      }
    }

  }
  const handleOpen = () => setOpen(!open);

  //POBIERANIE
  useEffect(() => {
    const data = jwt(token());
    const credentials = userCred().data
    axios.defaults.headers.common['Authorization'] = token();
    axios.get("http://localhost:8080/upc/v1/user-role/get-user-problems", {
      params: {
        email: data.sub,
      },
      auth : {
        username: credentials.email,
        password: credentials.password
      },
      headers:{
        "Content-Type": "application/json"
      },
      data:{}
    }).then(res => {
      console.log(res.data.content)
      const tab = res.data.content.map( u =>  ({
        uuid: u.uuid,
        userProblemId: u.uuid,
        userProblemStartDate:  DateFormat(u.userProblemStartDate) ,
        userProblemStatus:  u.userProblemStatus
      }))
      setProducts(tab)
      setLoading(false)
    })
  }, [])
  if (loading) {
    return (
      <div className="flex flex-col w-full h-full items-center justify-center">
        <Spinner color="amber"  className="h-1/2 w-1/2"></Spinner>
      </div>

    )
  }

  return (
    <div className="flex flex-col h-full">
      <div className="">
        <Button
          className=" bg-green-400 drop-shadow-md rounded-md text-white font-bold text-md p-2 hover:bg-green-500 m-2"
          onClick={() => { handleOpen() }}
        >
          DODAJ ZGŁOSZENIE
        </Button>
      </div>

      <Table headers={TABLE_HEAD} rows={products}></Table>
      <Dialog  className="flex flex-col w-full " open={open} handler={handleOpen} >
        <div className="">
          <DialogHeader>Nowe zgłoszenie</DialogHeader>
        </div>
        <DialogBody divider>
          <div className="grid gap-6">
            {showError && <p className="text-md text-red-500">Tresć zgłoszenia musi wynosić conajmniej 20 znaków!</p>}
            <Textarea label="Treść zgłoszenia" onChange={(e) => {
              setDescription(e.target.value);
              setShowError(false);
            }} rows={10} />
          </div>
        </DialogBody>
        <DialogFooter className="space-x-2">
          <Button color="red" onClick={() => {
            handleOpen();
          }}>
            Anuluj
          </Button>
          <Button variant="gradient" color="green" type="submit" onClick={() => {
            handleSubmit();
          }}>
            Wyślij zgłoszenie
          </Button>
        </DialogFooter>
      </Dialog>

      <Outlet></Outlet>
    </div>
  )
}