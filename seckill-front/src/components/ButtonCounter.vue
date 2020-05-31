<template>
<div id="button-counter">
    <!-- 插槽 -->
    <slot></slot>
    <button @click="count ++">==={{propName}}点击了我{{count}}次</button>

</div>

</template>

<script>
export default {

    name:'button-counter',
    props:['propName'],
    data(){
        return{
            count : 0
        }
    },
    methods:{

    }
    
}
</script>

<style lang="scss">

</style>


