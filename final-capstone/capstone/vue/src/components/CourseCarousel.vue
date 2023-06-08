<template>
<div class="container">
    <button @click="previous" class="prev-button"><span>&#171;</span></button>
    
    <div class="course-carousel">
        <div class="inner" >
            <h2 class="header">Top Courses</h2>
                <div class="course" v-for="course in courses" :key="course.id">
                        
                        <course-card  :course="course" />
                </div>
        </div>
    </div>
    <button @click="next" class="next-button"><span>&#187;</span></button>
 
  </div>
</template>

<script>

import CarolinaLakes from '../assets/GolfCourses/2_CarolinaLakesGolfClub.jpg'
import Heritage from '../assets/GolfCourses/4_HeritageGolfEagleTrace.jpg'
import WhisperingPines from '../assets/GolfCourses/620_WhisperingPines.jpeg'
import Muirfield from '../assets/GolfCourses/1209_Muirfield.jpeg'
import Riviera from '../assets/GolfCourses/1407_Riviera.jpg'
import Kinloch from '../assets/GolfCourses/1489_Kinloch.jpeg'
import CastlePines from '../assets/GolfCourses/2301_CastlePines.jpeg'
import Essex from '../assets/GolfCourses/2840_Essex.jpeg'
import Sebonack from '../assets/GolfCourses/2977_Sebonack.jpeg'
import KiaWah from '../assets/GolfCourses/3373_KiawahIsland.jpeg'
import TPCSawgrass from '../assets/GolfCourses/3384_TPCSawgrass.jpeg'
import Shinnecock from '../assets/GolfCourses/6268_Shinnecock.jpeg'
import Pete from '../assets/GolfCourses/6795_PeteDye.jpeg'
import OldTown from '../assets/GolfCourses/8231_OldTownClub.jpeg'
import WeKoPa from '../assets/GolfCourses/8852_WeKoPa.jpg'
import BearTrap from '../assets/GolfCourses/8996_BearTrapDunes.png'
import ShadowCreek from '../assets/GolfCourses/9014_ShadowCreek.jpeg'
import ErinHills from '../assets/GolfCourses/9393_ErinHills.jpg'
import Scioto from '../assets/GolfCourses/9533_Scioto.jpeg'
import CrystalDowns from '../assets/GolfCourses/9805_CrystalDownsCountryClub.jpeg'

import CourseCard from '../components/CourseCard.vue'



export default {
    name: "course-carousel",
    components: {
        CourseCard
    },
    data() {
        return {
            innerStyles: {},
            courses: [
                {
                    image: CarolinaLakes,
                    name: "Carolina Lakes Golf Club",
                    location: 'Fort Mill, South Carolina'
            
                },
                {
                    image: Heritage,
                    name: "Eagle Trace Golf Club",
                    location: "Coral Springs, Florida"
                },
                {
                    image: WhisperingPines,
                    name: "Whispering Pines Golf Club",
                    location: "Trinity, Texas"
                },
                {
                    image: Muirfield,
                    name: "Muirfield Golf Club",
                    location: "Dublin, Ohio"
                },
                {
                    image: Riviera,
                    name: "Riviera Country Club",
                    location: "Coral Gables, Florida"
                },
                {
                    image: Kinloch,
                    name: "Kinloch Golf Club",
                    location: "Manakin Sabot, Virginia"
                },
                {
                    image: CastlePines,
                    name: "Castle Pines Golf Club",
                    location: "Castle Rock, Colorado"
                },
                {
                    image: Essex,
                    name: "Essex County Country Club",
                    location: "Manchester, Massachusetts"
                },
                {
                    image: Sebonack,
                    name: "Sebonack Golf Club",
                    location: "Southampton, New York"
                },
                {
                    image: KiaWah,
                    name: "Kiawah Island Golf Resort",
                    location: "Kiawah Island, South Carolina"
                },
                {
                    image: TPCSawgrass,
                    name: "TPC Sawgrass",
                    location: "Ponte Vedra Beach, Florida"
                },
                {
                    image: Shinnecock,
                    name: "Shinnecock Hills Golf Club",
                    location: "Southampton, New York"
                },
                {
                    image: Pete,
                    name: "Pete Dye Golf Club",
                    location: "Bridgeport, West Virginia"
                },
                {
                    image: OldTown,
                    name: "Old Town Club",
                    location: "Winston Salem, North Carolina"
                },
                {
                    image: WeKoPa,
                    name: "We-Ko-Pa Golf Club",
                    location: "Fort McDowell, Arizona"
                },
                {
                    image: BearTrap,
                    name: "Bear Trap Dunes Golf Club",
                    location: "Ocean View, Delaware"
                },
                {
                    image: ShadowCreek,
                    name: "Shadow Creek Golf Course",
                    location: "North Las Vegas, Nevada"
                },
                {
                    image: ErinHills,
                    name: "Erin Hills Golf Course",
                    location: "Hartford, Wisconsin"
                },
                {
                    image: Scioto,
                    name: "Scioto Country Club",
                    location: "Powell, Ohio"
                },
                {
                    image: CrystalDowns,
                    name: "Crystal Downs Country Club",
                    location: "Frankfort, Michigan"
                }
             ],
        }
    },
    mounted() {

    },
    methods: {
        next() {
            setTimeout(() => this.courses.push(this.courses.shift()), 150);
        },
        previous() {
            this.courses.unshift(this.courses.pop());
        }
        
    }

}
</script>

<style scoped>

.container{
    background-color: #059262;
    width: 700px;
    display: flex;
    align-items: center;
    justify-content: center;
    height: 300px;
    border-radius: 10px;
    flex-direction: row;
    
}
.header{
    display: flex;
    justify-content: center;
    align-items: center;
    color: white;
}
.course-carousel {

}

.inner {
    white-space: nowrap;
    transition: transform 0.2s;
    width: 375px;
    overflow: hidden;
    
    
}

.course {
    display: inline-flex;
    width: 350px;
    height: 260px;
    margin-right: 20px;
    
}

.prev-button, .next-button {
    flex: 1;
  display: flex;
  
  align-items: center;
  justify-content: center;
    margin-bottom: 100px;
  height: 200px;
  width: 50px;
  background: none;
  border: none;
  font-size: 16px;
  cursor: pointer;
    color: white;
    /* background-color: #059262; */

}
.prev-button span, .next-button span{
    font-size: 50px;
    background-color: #059262;
    background-size: 10px;
    height: 60px;
    width: 60px;
    border-radius: 10px;
}

.prev-button span:hover, .next-button span:hover{
     box-shadow: 0 0 10px rgba(0, 0, 0, 0.5); 
  transition: box-shadow 0.3s ease;
}
</style>