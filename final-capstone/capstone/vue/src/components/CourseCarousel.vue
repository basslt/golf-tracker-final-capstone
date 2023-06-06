<template>
<div class="container">
    <button @click="previous">prev</button>
    
    <div class="course-carousel">
        <div class="inner" >
                <div class="course" v-for="course in courses" :key="course.id">
                        <course-card  :course="course" />
                </div>
        </div>
    </div>
    <button @click="next">next</button>
 
  </div>
</template>

<script>

import CarolinaLakes from '../assets/GolfCourses/2_CarolinaLakesGolfClub.jpg'
import Heritage from '../assets/GolfCourses/4_HeritageGolfEagleTrace.jpg'
import WhisperingPines from '../assets/GolfCourses/620_WhisperingPines.jpeg'
import Muirfield from '../assets/GolfCourses/1209_Muirfield.jpeg'
import Riviera from '../assets/GolfCourses/1407_Riviera.jpg'
import Kinloch from '../assets/GolfCourses/1489_Kinloch.jpeg'
// import CastlePines from '../assets/GolfCourses/2301_CastlePines.jpeg'
// import Essex from '../assets/GolfCourses/2840_Essex.jpeg'
// import Sebonack from '../assets/GolfCourses/2977_Sebonack'
// import KiaWah from '../assets/GolfCourses/3373_KiawahIsland.jpeg'
// import TPCSawgrass from '../assets/GolfCourses/3384_TPCSawgrass.jpeg'
// import Shinnecock from '../assets/GolfCourses/6268_Shinnecock.jpeg'
// import Pete from '../assets/GolfCourses/6795_PeteDye.jpeg'
// import OldTown from '../assets/GolfCourses/8231_OldTownClub.jpeg'
// import WeKoPa from '../assets/GolfCourses/8852_WeKoPa.jpg'
// import BearTrap from '../assets/GolfCourses/8996_BearTrapDunes.png'
// import ShadowCreek from '../assets/GolfCourses/9014_ShadowCreek.jpeg'
// import ErinHills from '../assets/GolfCourses/9393_ErinHills.jpg'
// import Scioto from '../assets/GolfCourses/9533_Scioto.jpeg'
// import CrystalDowns from '../assets/GolfCourses/9805_CrystalDownsCountryClub.jpeg'

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
                    name: "Carolina Lakes Golf Club"
                },
                {
                    image: Heritage,
                    name: "Eagle Trace Golf Club"
                },
                {
                    image: WhisperingPines,
                    name: "Whispering Pines Golf Club"
                },
                {
                    image: Muirfield,
                    name: "Muirfield Golf Club"
                },
                {
                    image: Riviera,
                    name: "Riviera Country Club"
                },
                {
                    image: Kinloch,
                    name: "Kinloch Golf Club"
                },
            //     {
            //         image: CastlePines,
            //         name: "Castle Pines Golf Club"
            //     },
            //     {
            //         image: Essex,
            //         name: "Essex County Country Club"
            //     },
            //     {
            //         image: Sebonack,
            //         name: "Sebonack Golf Club"
            //     },
            //     {
            //         image: KiaWah,
            //         name: "Kiawah Island Golf Resort"
            //     },
            //     {
            //         image: TPCSawgrass,
            //         name: "TPC Sawgrass"
            //     },
            //     {
            //         image: Shinnecock,
            //         name: "Shinnecock Hills Golf Club"
            //     },
            //     {
            //         image: Pete,
            //         name: "Pete Dye Golf Club"
            //     },
            //     {
            //         image: OldTown,
            //         name: "Old Town Club"
            //     },
            //     {
            //         image: WeKoPa,
            //         name: "We-Ko-Pa Golf Club"
            //     },
            //     {
            //         image: BearTrap,
            //         name: "Bear Trap Dunes Golf Club"
            //     },
            //     {
            //         image: ShadowCreek,
            //         name: "Shadow Creek Golf Course"
            //     },
            //     {
            //         image: ErinHills,
            //         name: "Erin Hills Golf Course"
            //     },
            //     {
            //         image: Scioto,
            //         name: "Scioto Country Club"
            //     },
            //     {
            //         image: CrystalDowns,
            //         name: "Crystal Downs Country Club"
            //     }
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
    background-color: rgba(134, 133, 133, 0.5);
    width: 500px;
    display: flex;
    align-items: center;
    justify-content: center;
    
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
    height: 250px;
    margin-right: 20px;
}

</style>