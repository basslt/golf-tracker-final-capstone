<template>
  <div>
      <div id="new-message">
          <div class="container">
             
          <form @submit.prevent>
              <div class="form"> 
                  <div class="close-button" @click="closeMessage"><i class="fa-solid fa-xmark" style="color: #000000;"></i></div>
                  <h2>Send Message</h2>
                  <div class="recepient-input">
                      <label for="username">Enter username: </label>
                      <input type="text" id="username" v-model="toUsername" required autofocus v-on:input="checkUsernames" />
                      <ul v-show="showNames">
                          <li v-for="username in filteredUsernames" v-bind:key="username">{{username}}</li>
                      </ul>
                  </div>
                  <div class="content-input">
                      <label for="content">Message </label>
                      <textarea name="content" id="content" cols="30" rows="10" required @input="updateContent($event.target.value)"></textarea>
                  </div>
                  </div>
              <div>
                  <button class="submit-button" type="submit" v-on:click="sendMessage()"><span> Send</span></button>
              </div>
              
          </form>
          
          </div>
        
      </div>
  </div>
</template>

<script>
import userService from '../services/UserService'
import messageService from '../services/MessageService'

export default {
    name: 'new-message',
    data() {
        return {
            toUsername: "",
            allUsernames: [],
            showNames: false,
            toUser: [],
            message: {
                senderId: '',
                receiverId: '',
                content: '',
                leagueId: null,
                type: 'Message',
                timestamp: Date.now()
            }
        }
    },
    created() {
        userService.getAllUsernames().then( (response) => {
            this.allUsernames = response.data;
        })
    },
    methods: {
        checkUsernames() {
            this.showNames = this.toUsername.length > 0;
        },
        sendMessage() {
            const user = this.$store.getters.getUser;
            this.message.senderId = user.id;
            userService.getUserIdByUsername(this.toUsername).then( (response) => {
                this.message.receiverId = response.data;
                messageService.createMessage(this.message).then( (response) => {
                if (response.status === 201) {
                     console.log("Success");
                     this.$emit('close');
                }
            }).catch((error) => {
                if (error.response) {
                this.errorMsg = "Error submitting new message";
                } else if (error.request) {
                this.errorMsg = "Error submitting new message. Server could not be reached";
                } else {
                this.errorMsg = "Error submitting new message";
                }
            })
            });
            
        },
        closeMessage() {
            this.$emit('close');
        },
        updateContent(value) {
            this.message.content = value;
        }
    },
    computed: {
        filteredUsernames() {
            return this.allUsernames.filter( (username) => {
                return username.toLowerCase().includes(this.toUsername.toLowerCase())
            });
        }
    }

}
</script>

<style scoped>

#new-message {
  position: fixed;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center; 
} 


.container {
  background-color: white;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
  max-width: 400px; /* Adjust the width as needed */
  width: 100%; 
  display: flex;
  justify-content: center;
  align-items: center;
}

.form h2{
    display: flex;
    justify-content: center;
}



.recepient-input{
display: flex;
justify-content: center;
padding: 5px;
}
.recepient-input label{
    display: flex;
    align-items: center;
}
.recepient-input input{
    display: flex;
    align-items: center;
    margin: 10px;
    padding: 10px;
    border: none;
    border-radius: 5px;
background-color: #dddddd;
  color: #333;
  transition: box-shadow 0.3s ease;
}
.content-input{
    display: flex;
    flex-direction: column;
    border: none;
    
}



.content-input label {
    display: flex;
    justify-content: center;
    padding: 10px;
    font-weight: bold;
}
.content-input textarea{
       border-radius: 5px;
background-color: #dddddd;
  color: rgb(36, 36, 36);
  transition: box-shadow 0.3s ease;
  border: none;
}

.submit-button {
 display: flex;
 justify-content: center;
 align-items: center;
 flex-direction: column;
  padding: 10px 40px;
  background-color: #ffde59;
  color: white;
  border: none;
  border-radius: 15px; /* Adjust the value to control the roundness */
  cursor: pointer;
  margin-top: 20px;
  margin-bottom: 20px;
  margin-left: 105px;
}
.submit-button span {
  font-size: 20px; /* Adjust the font size for the login text */
  font-weight: bold;
  

}

.login-button:hover {
  background-color: #fce279;
}

.exit-button {
    position: absolute;
    right: 35%;
    top: 10%;
}
.close-button{
    display: flex;
    justify-content: flex-end;
    font-size: 25px;
    font-weight: bold;
    cursor: pointer;
    

}
</style>