<template>
    <v-app>
        <v-container>
            <v-row class="pa-15">
                <h2 class="mx-auto">Direct Non-Labour Costs</h2>
            </v-row>
            <v-row>
                <v-col cols="12" class="ml-1">
                    <v-text-field v-model="search" append-icon="mdi-magnify" label="Search" single-line hide-details></v-text-field>
                </v-col>
                <v-col cols="12">
                    <v-data-table :headers="headers" :items="costList" :search="search" @click:row="change"></v-data-table>
                </v-col>
            </v-row>
        </v-container>
    </v-app>
</template>
<script>
const axios = require("axios").default;
import router from "../router";

export default {
    data() {
    return {
      search: "",
      headers: [
        {
          text: "Type",
          align: "start",
          filterable: true,
          value: "type",
        },
        { text: "Description", value: "description" },
        { text: "In Kind Contribution", value: "in_kind"},
        { text: "Actual Cost", value: "actual_cost"},
      ],
      costList: [
        { type: "Travel", description: "CI & RA Travel to Melbourne for conference", in_kind: "-", actual_cost: "3,250.00"},
        { type: "Facility Hire", description: "Menzies - Zeiss LSM510", in_kind: "-", actual_cost: "6,099.66"},
      ],
      costListTemp: [],
      costListFixed: [],
    };
  },
  methods: {
    say(e) {
      alert(e);
    },
    load_project_cost() {
      axios.get("http://10.36.241.204:8080/api/expenseList/" + this.$store.state.projectIndex).then((resp) => {
        console.log(resp.data); //use resp.data[0].name for arrays
        this.costListTemp = resp.data;
        var obj;
        for (var i = 0; i < this.costListTemp.length; i++) {
          obj = {
            id: this.costListTemp[i].id,
            type: this.costListTemp[i].type,
            description: this.costListTemp[i].description,
            in_kind: this.costListTemp[i].inKindPercent,
            actual_cost: this.costListTemp[i].actualCost,
          };
          this.costListFixed.push(obj);
        }
        console.log(this.costListFixed);
      });
    },
    change(item) {
    //   something
    },
  },
    mounted() {
        if (this.$store.state.projectIndex == -1) {
            this.$router.push("/");
        }
        this.load_project_cost();
    },
};
</script>