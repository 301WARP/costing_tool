<template>
  <div>
    <v-app>
      <!-- <v-div> -->
      <v-card>
        <v-card class="d-flex justify-center mb-6">
          <v-alert
            color="red"
            elevation="24"
            type="error"
            max-width="1200"
            class="d-flex justify-center mb-6"
            v-if="connectError != 0"
            >{{
              "ERROR STATUS: " +
              connectErrorStatus +
              " | ERROR MSG: " +
              connectErrorMsg +
              " | ERROR CODE: " +
              connectErrorCode
            }}</v-alert
          >
        </v-card>
        <v-row class="pa-15 mt-1">
          <h2 class="mx-auto">Projects</h2>
        </v-row>
        <v-row>
          <v-col cols="12" class="px-6">
            <v-alert
              color="red"
              elevation="9"
              prominent
              text
              type="error"
              :value="editPushed"
            >
              {{ errorMessage }}
            </v-alert>
          </v-col>
        </v-row>
        <v-card-title>
          <v-text-field
            v-model="search"
            append-icon="mdi-magnify"
            label="Search"
            single-line
            hide-details
          >
          </v-text-field>
        </v-card-title>
        <v-data-table
          :headers="headers"
          :items="projectListFixed"
          :search="search"
          @click:row="change"
        >
        </v-data-table>
        <v-row class="py-5">
          <v-col cols="4" align="center">
            <v-btn
              @click="sendError('You must be an administrator to do this.')"
              color="primary"
              elevation="4"
              outlined
              large
            >
              Export Data
            </v-btn>
          </v-col>
          <v-col cols="4" align="center">
            <v-btn
              color="primary"
              elevation="4"
              outlined
              large
              @click="
                sendError(
                  'You must be an administrator to edit the list or remove projects.'
                )
              "
            >
              Edit Projects
            </v-btn>
          </v-col>
          <v-col cols="4" align="center">
            <v-btn
              @click="change(0)"
              color="primary"
              elevation="4"
              outlined
              large
            >
              Create Project
            </v-btn>
          </v-col>
        </v-row>
      </v-card>
      <!-- </v-div> -->
    </v-app>
  </div>
</template>
<script>
const axios = require("axios").default;
import router from "../router";

export default {
  data() {
    return {
      search: "",
      editPushed: false,
      errorMessage: "",
      headers: [
        {
          text: "Name",
          align: "start",
          filterable: true,
          value: "name",
        },
        { text: "Role", value: "role" },
        { text: "Contract", value: "contract" },
      ],
      projectList: [],
      projectListFixed: [],
      projectIndex: 1,
      connectError: 0,
      connectErrorMsg: "",
      connectErrorStatus: 0,
      connectErrorCode: 0,
    };
  },
  methods: {
    say(e) {
      alert(e);
    },
    async load_projects_list() {
      var errorData = { status: 200 };
      await axios
        .get("http://10.36.241.204:8080/api/projects/")
        .then((resp) => {
          this.connectError = 0;
          this.researcher_list = resp.data;
          var obj;
          for (var i = 0; i < this.researcher_list.length; i++) {
            obj = {
              name: this.researcher_list[i].name,
              id: this.researcher_list[i].id,
              role: "CI",
              contract: "Full Time",
            };
            this.projectListFixed.push(obj);
          }
          console.log(this.projectListFixed);
        })
        .catch(function (error) {
          console.log(error.toJSON());
          errorData = error.toJSON();
        });
      if (errorData.status != 200) {
        this.connectErrorStatus = errorData.status;
        this.connectError = 1;
        this.connectErrorMsg = errorData.message;
        this.connectErrorCode = errorData.code;
      }
    },
    change(item) {
      if (item == 0) {
        this.$store.commit("setProjectIndex", item);
      } else {
        this.$store.commit("setProjectIndex", item.id);
      }
      router.push("/details");
    },
    sendError(m) {
      this.errorMessage = m;
      this.editPushed = true;
      setTimeout(() => {
        this.editPushed = false;
      }, 5000);
    },
  },
  mounted() {
    this.load_projects_list();
    this.$store.commit("setProjectIndex", -1);
  },
};
</script>
<style>
/* .v-btn {
  margin-top: 50px;
  margin-left: auto;
  margin-right: auto;
  /* margin-left: 250px; */
/* }  */
</style>
