<template>
    <el-container>
        <el-container>
            <el-card class="box-card"  style="color: black" shadow="always">
                <div slot="header" class="clearfix" >
                    <span>问卷总数</span>
                </div>
                <div class="text item">
                    {{0 }}
                </div>
            </el-card>
            <el-card class="box-card" style="background-color: dodgerblue">
                <div slot="header" class="clearfix" >
                    <span>已发放</span>
                </div>
                <div class="text item">
                    {{0}}
                </div>
            </el-card>
            <el-card class="box-card"  style="background-color: mediumseagreen">
                <div slot="header" class="clearfix">
                    <span>编辑中</span>
                </div>
                <div class="text item">
                    {{0}}
                </div>
            </el-card>
            <el-card class="box-card"  style="background-color: orange">
            <div slot="header" class="clearfix">
                <span>已结束</span>
            </div>
            <div class="text item">
                {{0}}
            </div>
        </el-card>
        </el-container>
    </el-container>
</template>

<script>
import { mapGetters, mapMutations, mapActions } from 'vuex'

export default {

    data() {
        return {

        }
    },
    mounted() {

    },
    computed: {

    },
    methods: {

    }
}
</script>

<style>
.text {
    align-items: center;
    margin-top: 20px;
    font-size: 40px;
}

.item {
    margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
    display: table;
    content: "";
}
.clearfix:after {
    clear: both
}

.box-card {
    font-weight: bolder;
    color: white;
    box-shadow: gray;
    border-radius: 20px;
    width: 100%;
    margin: 30px;
    height: 200px;
    font-size: 30px;
}
</style>
