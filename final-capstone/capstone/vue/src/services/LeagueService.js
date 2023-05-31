import axios from 'axios';

export default {

 getLeagues(id){
   return axios.get(`/${id}`)
 }

}