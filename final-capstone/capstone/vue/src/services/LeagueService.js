import axios from 'axios';

export default {

 getLeagues(){
   return axios.get('league')
 }

}