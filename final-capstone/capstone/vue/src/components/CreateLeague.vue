<template>
<div class="background" >
  <div class="container" >    
    <div class="add-league">
      <form v-on:submit.prevent>
        <div class="heading">
          <h2>Create League</h2>
        </div>
        <p class="description">Explanation about creating a league goes here</p>
        <div class="form-group">
          <label class= "input-text" for="leagueName">League Name:</label>
          <input type="text" id="leagueName" v-model="league.leagueName" required>
        </div>
        <div class="buttons">
          <button type="submit" v-on:click="createLeague()">Create</button>
          <button type="button" v-on:click="closeForm()">Cancel</button>
        </div>
      </form>
      
    </div>
  </div>
  </div>
</template>

<script>
import leagueService from '../services/LeagueService';

export default {
  data() {
    return {
      league: {
        leagueName: '',
        organizerId: ''
      },
      newLeagueId: '',
      //showCreateForm: false
    };
  },
  methods: {
    createLeague() {
      const user = this.$store.getters.getUser;
      this.league.organizerId = user.id;
      leagueService.addLeague(this.league)
        .then( (response) => {
          this.newLeagueId = response.data.leagueId;
          this.$router.push({ name: 'SelectedLeague', params: { id: this.newLeagueId}})
          console.log('League created!');
        })
        .catch(error => {
          console.error('Failed to fetch league ID:', error);
        });
    },
    closeForm() {
      this.$store.commit('SET_SHOW_CREATE_FORM_STATUS', false);
    }
  },
  // created() {
  //   this.showCreateForm = this.$store.state.showCreateForm;
  // }
}
</script>

<style scoped>

.background {
  position: absolute;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  background-color: rgba(0, 0, 0, 0.5); /* Adjust the alpha value (0.5) to control the transparency */
  display: flex;
  justify-content: center;
  align-items: center;
}

.form-group{
  display: flex;
  white-space: nowrap;
  margin-top: 30px;
  padding: 10px;
}

.add-league {
  display: flex;
  flex-direction: column;
  background-color: whitesmoke;
  align-items: center;
  justify-content: center;
   /* box-shadow: 0 0 10px #004d33; */
  padding: 20px;
  margin-bottom: 20px; 
   border-radius: 10px;
}

.description{
  position: flex;
  padding: 10px;
}

.input-text{
  margin-top: 7px;
}

.buttons {
  display: flex;
  justify-content: space-between;

}

form {
 background-color: rgba(255, 255, 255, 0.8);
  padding: 20px;
  border-radius: 5px;
}

 input[type="text"]
   {
 padding: 10px;
  border-width: 2px;
  border-radius: 10px;
  border-color: black;
  background-color: #fffbfb;
  font-size: 16px;
  color: rgb(0, 0, 0);
  transition: box-shadow 0.3s ease;

 }

label {
  margin-right: 0.5rem;
  color:#000000;
  font-weight: bold;
  padding-bottom: 10px;
}

 input {
  width: 100%;
  height: 2rem;
  padding: 0.25rem;
  font-size: 1rem;
}

button{
display: inline-block;
  padding: 10px 40px;
  background-color: #ffde59;
  color: white;
  font-weight: bolder;
  border: none;
  border-radius: 15px; /* Adjust the value to control the roundness */
  cursor: pointer;
  margin-top: 20px;
  margin-left: 20px;
  margin-right: 20px;
  margin-bottom: 20px;
  font-size: 16px;
}

button:hover{
   background-color:#fce279;
}

.heading{
  
  display: flex;
  justify-content: center;
  font-size: 24px;
  font-weight: bold;
  color: #000000;
  align-items: center;
  
}

</style>
