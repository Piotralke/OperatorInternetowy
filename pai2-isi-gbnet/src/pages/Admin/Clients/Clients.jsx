import { useEffect, useState } from "react";
import Table from "../../../components/Table";
import axios from "axios";
import { Outlet } from "react-router-dom";
import { Spinner } from "@material-tailwind/react";
import { useAuthHeader } from "react-auth-kit";
const TABLE_HEAD = [
  { name: "Imię", key: "FirstName" },
  { name: "Nazwisko", key: "LastName" },
  { name: "Email", key: "Email" },
  { name: "Nr telefonu", key: "phone" },
  { name: "Adres", key: "address" },
  { name: "NIP", key: "nip" },
  { name: "Szczegóły", key: null },
];

export default function Clients() {
  const token = useAuthHeader();
  const [users, setUsers] = useState([]);
  const [loading, setLoading] = useState(true);
  useEffect(() => {
    async function fetchData() {
      axios.defaults.headers.common["Authorization"] = token();
      await axios
        .get("http://localhost:8080/upc/v1/worker-role/user/all")
        .then((res) => {
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
        });
    }
    fetchData();
  }, []);
  if (loading) {
    return (
      <div className="flex flex-col w-full h-full items-center justify-center">
        <Spinner color="amber" className="h-1/2 w-1/2"></Spinner>
      </div>
    );
  }
  return (
    <div className="flex flex-col h-full justify-start">
      <Table data-testid="table" headers={TABLE_HEAD} rows={users}></Table>
      <Outlet></Outlet>
    </div>
  );
}
