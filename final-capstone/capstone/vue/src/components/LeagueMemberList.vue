<template>
<div class="container">
    <div class="background">
 
        <div class="table-container">
            <!-- <ul v-for="member in leagueMembers" v-bind:key="member.id">
                <li>{{member.username}}<i class="fa-solid fa-message" style="color: #059262;"></i></li>
            </ul> -->
            <table>
                <thead>
                    <tr>
                        <th>User</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="member in leagueMembers" v-bind:key="member.id">
                        <td>{{member.username}}</td>
                        <td><i class="fa-solid fa-message" style="color: #059262;"></i></td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>

</div>
</template>

<script>
import userService from '../services/UserService'

export default {
    props: {
        leagueId: {
            type: Number,
            required: true
        }
    },
    data() {
        return {
            leagueMembers: [],
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
        }
    },
    created() {
        this.getLeagueMembers();
    }

}
</script>

<style scoped>

.container {

}

.table-container {
    width: 90%;
    height: 100px;
    background-color: lightgrey;
    margin: auto;
    vertical-align: middle;
}

.background {
    background-color: #059262;
    width: 150px;
    height: 150px;
    display: flex;
    background-size: cover;
    background-position: center;
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

tr {
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
    /* height: 100px; */
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
 }


</style>