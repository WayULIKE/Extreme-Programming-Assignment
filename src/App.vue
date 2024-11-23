<template>
  <div id="app">
  <el-container>
    <el-header style="background-color: #4c535a">
    <img src="@/assets/logo2.png" alt="" style="width: 50px; position: relative; top: 4px;">
    <span style="font-size:25px;margin-left: 15px; top: 1px; color: white">通讯录</span>
    </el-header>
  </el-container>

  <el-container>
    <el-aside style="overflow: hidden; min-height: 100vh; background-color: #545c64; width: 250px">
    <el-menu :default-active="$route.path" router
          default-active="/"
          class="el-menu-vertical-demo"
          @open="handleOpen"
          @close="handleClose"
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#ffd04b">
          <el-menu-item index="/">
                      <i class="el-icon-menu"></i>
                      <span slot="title">通讯录系统</span>
                    </el-menu-item>
          <el-submenu index="1">
            <template slot="title">
              <i class="el-icon-location"></i>
              <span>操作</span>
            </template>
            <el-menu-item-group>
              <el-menu-item index="/inputclass">通讯录操作</el-menu-item>
            </el-menu-item-group>
            </el-menu-item-group>
          </el-submenu>

        </el-menu>
      </el-col>
    </el-row>
    </el-aside>
    <el-main>
    <router-view/>
    </el-main>
  </el-container>
  </div>
</template>

<style>
.el-menu{
   border-right: none !important;
}
</style>
