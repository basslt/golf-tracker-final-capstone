<template>
<div class="container">
    <h1></h1>
    <div class="background">
 
        <div class="table-container">
            <!-- <ul v-for="member in leagueMembers" v-bind:key="member.id">
                <li>{{member.username}}<i class="fa-solid fa-message" style="color: #059262;"></i></li>
            </ul> -->
            <table>
                <thead>
                    <tr>
                        <th class="headline">Members</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody class="list">
                    <tr v-for="member in leagueMembers" v-bind:key="member.id">
                        <td>{{member.username}}</td>
                        <td class="message"><i class="fa-solid fa-message" style="color: #00000;" @click="openMessageForm(member.id)"></i></td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
    <league-message-form v-if="showMessageForm" :messageRecepient="checkedMemberId" @close="close"/>

</div>
</template>

<script>
import userService from '../services/UserService'
import LeagueMessageForm from '../components/LeagueMessageForm.vue'

export default {
    components: {
        LeagueMessageForm
    },
    props: {
        leagueId: {
            type: Number,
            required: true
        }
    },
    data() {
        return {
            leagueMembers: [],
            showMessageForm: false,
            checkedMemberId: null
        }
    },
    methods: {
        getLeagueMembers() {
            userService.findUsersInLeague(this.leagueId).then( (response) => {
                this.leagueMembers = response.data;
                console.log(response.data);
            })
            .catch(error => {
                console.log(error);
            });
        },
        openMessageForm(memberId) {
            this.showMessageForm = true;
            this.checkedMemberId = memberId;
            console.log(memberId)
        },
        close(){ 
            this.showMessageForm = false;
            this.checkMember = null;
        }
    },
    created() {
        this.getLeagueMembers();
    }

}
</script>

<style scoped>

.container {
display: flex;
flex-direction: column;
justify-content: center;
align-items: center;

}

.list {
    width: 150px;
    flex-direction: column;
    display: flex;
    align-items: center;
    color: white;
    padding: 5px;
}

.list td{
    font-size: 20px;
}

.background {
    background-color: #059262;
    height: 400px;
    display: flex;
    /* background-size: cover; */
    background-position: center;
    border-radius: 15px;
}
.headline{
    display: flex;
    justify-content: center;
    align-self: center;
    align-items: center;
    color: white;
     margin: 10px;
     border-bottom: white solid;
     padding: 5px;
}


/* li {
    margin-bottom: 2%;
}

i {
    margin-left: 5%;
} */

/* .table-container {
    position: absolute;
    top: 22%;
    left: 11%;
    width: 62%;
    background-color: lightgrey;
} */


/* table {
    width: 100%;
    border-collapse: collapse;
    text-align: center;
} */
/* 
th {
    padding-bottom: 2%;
    border-bottom: 1px solid black;
    text-align: center;
} */

/* tr {
    margin-bottom: 10%;
}

td {
    padding-bottom: 1%;
    padding-top: 1%;
    vertical-align: center; 
    width: 5%;
    height: 5%;
    text-align: center;
 } 

tbody {
    
     overflow: auto;
     display: block;
}

th {
    padding-bottom: 1%;
    padding-top: 1%;
    vertical-align: center; 
    width: 5%;
    height: 5%;
    text-align: center;
}

 table {
     height: 100px;
     overflow: auto;
     display: block;
 } */


</style>