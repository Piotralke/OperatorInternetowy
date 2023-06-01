import userPic from "../assets/userPic.jpg"

export default function AccountData(){
    
    const user = {
        accountId: "213742069",
        name: "Jan",
        surname: "Dyrduł",
        pic: userPic
    }
    
    return(
        <div className="flex flex-col w-1/4 m-4 bg-gray-700 h-1/2">
            <div className="flex flex-row p-4 border-b border-gray-600">
                <text className="flex-grow text-white">Numer klienta</text>
                <text className="text-blue-300">{user.accountId}</text>
            </div>
            <div className="flex flex-col items-center justify-center p-4">
                <div className="flex flex-row w-full p-5 m-4 bg-gray-800 ">
                    <img className="w-10 h-10 rounded-full" src={user.pic}></img>
                    <div className="flex flex-col">
                        <div className="text-lg text-blue-300">{user.name} {user.surname}</div>
                    </div>
                </div>
                <div>dupa</div>
                <div></div>
            </div>
        </div>
    )
}