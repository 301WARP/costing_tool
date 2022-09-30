<script setup></script>

<template>
  <v-app>
    <!-- <h2>Title or Heading Here</h2> -->
  <div>
    <v-tabs v-model="tab" align-with-title grow  background-color="#1865BD"
          dark
          height="65"
          show-arrows>
      <v-tabs-slider color="#9DCFF0"></v-tabs-slider>
      <v-tab to="/" dark>
        Home
      </v-tab>
      <v-tab to="/details">
        Details
      </v-tab>
      <v-tab to="/researcher">
        Researchers
      </v-tab>
      <v-tab to="/costs">
        Costs
      </v-tab>
      <v-tab to="/clearance">
        Clearance Form
      </v-tab>
    </v-tabs>
    <RouterView></RouterView>
  </div>
</v-app>
</template>

<style></style>
