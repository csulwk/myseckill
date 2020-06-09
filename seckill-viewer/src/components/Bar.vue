<template>
    <div id="bar">
        bar --- {{getUsername}}
    </div>
</template>

<script>
    export default{
        name:'bar',
        data(){
            return{

            }
        },
        computed:{
            getUsername: function(){
                return this.$store.getters.getUsername
            }
        }
    }


</script>

<style lang="scss">
</style>