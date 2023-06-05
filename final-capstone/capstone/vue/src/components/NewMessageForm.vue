<template>
  <div>
      <div id="new-message">
          <div class="container">
          <form @submit.prevent>
              <div class="form">
                  <h2>Send a Message</h2>
                  <div class="recepient-input">
                      <label for="username">To user: </label>
                      <input type="text" id="username" v-model="toUsername" required autofocus v-on:input="checkUsernames" />
                      <ul v-show="showNames">
                          <li v-for="username in filteredUsernames" v-bind:key="username">{{username}}</li>
                      </ul>
                  </div>
                  <div class="content-input">
                      <label for="content">Message: </label>
                      <textarea name="content" id="content" cols="30" rows="10" required @input="updateContent($event.target.value)"></textarea>
                  </div>
                  <button class="exit-button" v-on:click="closeMessage">x</button>
              </div>
              <div>
                  <button type="submit" v-on:click="sendMessage()">Send Message</button>
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
  background-color: rgba(0, 0, 0, 0.5); /* Adjust the alpha value (0.5) to control the transparency */
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
  display: block;
  /* flex-direction: column;
  justify-content: center;
  align-items: flex-end; */
}

.exit-button {
    position: absolute;
    right: 35%;
    top: 10%;
}

</style>