import { Textarea, Card, List, ListItem, ListItemPrefix, IconButton, Checkbox, Typography, Spinner, Input, Button } from "@material-tailwind/react";
import React, { useState, useEffect, useRef } from "react";
import axios from "axios";
import { AiOutlineArrowLeft, AiOutlineArrowRight } from "react-icons/ai";
import { useAuthHeader,useAuthUser } from "react-auth-kit";
export default function AdminNotices() {
    const [loading, setLoading] = useState(true);
    const [users, setUsers] = useState([]);
    const [selectedItems, setSelectedItems] = useState([]);
    const [searchText, setSearchText] = useState("");
    const [active, setActive] = React.useState(1);
    const itemsPerPage = 9;
    const [description, setDescription] = useState("")
    const userCred = useAuthUser()


    useEffect(() => {
        async function fetchUsers() {
            const credentials = userCred().data
            const res = await axios.get("http://localhost:8080/upc/v1/worker-role/user/all",{
                auth : {
                  username: credentials.email,
                  password: credentials.password
                },
                headers:{
                  "Content-Type": "application/json"
                },
                data:{}
              });
            const users = res.data.content.map((u) => ({
                uuid: u.uuid,
                FirstName: u.firstName,
                LastName: u.lastName,
                Email: u.email,
                phone: u.phoneNumber,
                address: u.address,
                nip: u.nip,
            }));
            setUsers(users);
            setLoading(false);
        }
        fetchUsers();
    }, []);

    useEffect(() => {
        setActive(1);
    }, [searchText]);

    const totalItems = users.length;
    const filteredUsers = users.filter((user) =>
        user.Email.toLowerCase().includes(searchText.toLowerCase())
    );
    const totalPages = Math.ceil(filteredUsers.length / itemsPerPage);

    const next = () => {
        if (active === totalPages) return;
        setActive(active + 1);
    };

    const prev = () => {
        if (active === 1) return;
        setActive(active - 1);
    };
    async function handleSubmit() {
        if (description?.length < 20) {
            handleError(1);
        } else if(selectedItems.length<1){
            handleError(2)
        }
        else {
            const data = {
                description: description,
                clientsUuid: selectedItems
            }
            const credentials = userCred().data
            const response = await axios.post("http://localhost:8080/upc/v1/worker-role/save-notice", data,{
                auth : {
                  username: credentials.email,
                  password: credentials.password
                },
                headers:{
                  "Content-Type": "application/json"
                },
                data:{}
              })
            console.log(response);
            if (response.status === 200) {
                const tab = JSON.parse(localStorage.getItem("notifications"));
                let newTab;
                const message = {
                    message: `Pomyślnie wysłano powiadomienia do użytkowników`,
                    type: "SUCCESS"
                  }
                  if(tab)
                  {
                    newTab = [...tab,message];
                  }else{
                    newTab = [message];
                  }
                

                window.localStorage.setItem("notifications", JSON.stringify(newTab));
                window.dispatchEvent(new Event("storage"))
                window.location.reload();
            }
        }


    }
    const handleSelectAll = (event) => {
        if (event.target.checked) {
            setSelectedItems(users.map((user) => user.uuid));
        } else {
            setSelectedItems([]);
        }
    };

    const handleSelectItem = (event, itemId) => {
        if (event.target.checked) {
            setSelectedItems((prevSelected) => [...prevSelected, itemId]);
        } else {
            setSelectedItems((prevSelected) =>
                prevSelected.filter((id) => id !== itemId)
            );
        }
    };
    function handleError (type) {
        const tab = JSON.parse(localStorage.getItem("notifications"));
        let newTab;
        const mes = type === 1 ? "Tresć zgłoszenia musi wynosić conajmniej 20 znaków!":"Nie wybrano żadnego użytkownika!";
        const message = {
          message: mes,
          type: "ERR"
        }
        if(tab)
        {
          newTab = [...tab,message];
        }else{
          newTab = [message];
        }
        
        window.localStorage.setItem("notifications",JSON.stringify(newTab));
        window.dispatchEvent(new Event("storage"))
    }
    const handleSearch = (event) => {
        setSearchText(event.target.value);
    };

    const getPaginatedUsers = () => {
        const startIndex = (active - 1) * itemsPerPage;
        const endIndex = startIndex + itemsPerPage;
        return filteredUsers.slice(startIndex, endIndex);
    };

    if (loading) {
        return (
            <div className="flex flex-col w-full h-full items-center justify-center">
                <Spinner color="amber"  className="h-1/2 w-1/2"></Spinner>
            </div>
        );
    }

    return (
        <div className="flex flex-col xl:flex-row w-full bg-gray-300 h-full items-center justify-center xl:py-32 xl:p-4 xl:space-x-8 space-y-8 2xl:space-y-0">
            <div className="flex flex-col w-3/4 2xl:w-1/2 h-full">
                <div className="flex h-3/4">
                    <Textarea onChange={(e) => { setDescription(e.target.value)}} color="amber" className="h-full" label="Treść powiadomienia" />
                </div>
                <div className="flex flex-col justify-center items-center mt-3">
                    <Button onClick={handleSubmit} color='amber'>Wyślij</Button>
                </div>
            </div>
            <div className="w-3/4 2xl:w-1/2 h-full">
                <Card className="p-4 h-full ">
                    <div className="flex items-center justify-between mb-4">
                        <Input
                            type="text"
                            color="amber"
                            placeholder="Wyszukaj użytkownika"
                            value={searchText}
                            onChange={handleSearch}
                        />
                    </div>
                    <List className="flex-grow">
                        <ListItem className="p-0">
                            <label className="px-3 py-2 flex items-center w-full cursor-pointer">
                                <ListItemPrefix>
                                    <Checkbox
                                        color="amber"
                                        ripple={false}
                                        className="hover:before:opacity-0"
                                        containerProps={{
                                            className: "p-0",
                                        }}
                                        checked={
                                            selectedItems.length === totalItems && totalItems > 0
                                        }
                                        onChange={handleSelectAll}
                                    />
                                </ListItemPrefix>
                                <Typography color="blue-gray" className="font-medium truncate">
                                    Zaznacz wszystko
                                </Typography>
                            </label>
                        </ListItem>
                        {getPaginatedUsers().length > 0 ? getPaginatedUsers().map((user, index) => (
                            <ListItem className="p-0" key={user.uuid}>
                                <label className="px-3 py-2 flex items-center w-full cursor-pointer">
                                    <ListItemPrefix>
                                        <Checkbox
                                            color="amber"
                                            ripple={false}
                                            className="hover:before:opacity-0"
                                            containerProps={{
                                                className: "p-0",
                                            }}
                                            checked={selectedItems.includes(user.uuid)}
                                            onChange={(event) => handleSelectItem(event, user.uuid)}
                                        />
                                    </ListItemPrefix>
                                    <Typography color="blue-gray" className="font-medium truncate">
                                        {user.Email}
                                    </Typography>
                                </label>
                            </ListItem>
                        )) : <div className="self-center font-bold">Brak wyników wyszukiwania</div>}

                    </List>
                    <div className="flex items-center gap-8 justify-center">
                        <IconButton
                            size="sm"
                            color="amber"
                            onClick={prev}
                            disabled={active === 1}
                        >
                            <AiOutlineArrowLeft strokeWidth={2} className="h-4 w-4" />
                        </IconButton>
                        <Typography color="black" className="font-normal">
                            Page{" "}
                            <strong className="text-amber-500">{active}</strong> of{" "}
                            <strong className="text-amber-500">{totalPages}</strong>
                        </Typography>
                        <IconButton
                            size="sm"
                            color="amber"
                            onClick={next}
                            disabled={active === totalPages}
                        >
                            <AiOutlineArrowRight strokeWidth={2} className="h-4 w-4" />
                        </IconButton>
                    </div>
                </Card>
            </div>
        </div>
    );
}
