import {MdCancel} from 'react-icons/md'
export default function OrderCancel(){
    return(
        <div className="flex flex-col bg-blue-gray-600 w-full h-full items-center justify-center">
            <MdCancel className='text-amber-500 w-1/3 h-1/3'/>
            <span className='text-white text-2xl'>Płatność została anulowana!</span>
        </div>
    )
}