import Axios from "../../util/Axios"



export const changePasswordPost = async(filterData)=>
{
    return Axios
    .post('users/changePassword', filterData)
    .then((response)=>response.data);
}


export const getAllUsers = async()=>{
    return Axios
    .get('users/allUsers')
    .then((response)=>response.data);
}