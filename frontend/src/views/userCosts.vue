<template>
  <v-app>
    <v-container>
      <v-row class="pa-15">
        <h2 class="mx-auto">Direct Non-Labour Costs</h2>
      </v-row>

      <!-- Add Cost fields -->
      <v-row v-if="addingCost">
        <v-card width="100%" class="mb-15" elevation="3">
          <v-row class="px-5 pt-5">
            <v-col cols="6">
              <v-select
                :items="costDropdownList"
                label="New Cost Type"
                v-model="newCostType"
              ></v-select>
            </v-col>
            <v-col align="right" cols="6" class="pb-8">
              <v-btn
                @click="cancel()"
                color="primary"
                elevation="4"
                outlined
                large
                class="mr-3"
              >
                Cancel</v-btn
              >
              <v-btn
                @click="addCost()"
                color="primary"
                elevation="4"
                outlined
                large
              >
                Add Cost</v-btn
              >
            </v-col>
          </v-row>
        </v-card>
      </v-row>

      <!-- Travel Cost -->
      <v-row v-if="currentCostType == 1">
        <v-card width="100%" class="mb-15" elevation="3">
          <v-card-title>Travel</v-card-title>
          <v-row class="px-5">
            <v-col cols="12">
              <v-text-field
                label="Description"
                :value="currentDescription"
                v-model="currentDescription"
              >
              </v-text-field>
            </v-col>
          </v-row>
          <v-row class="px-5">
            <v-col cols="4">
              <v-text-field label="Number of People Travelling"></v-text-field>
            </v-col>
            <v-col cols="4">
              <v-text-field label="Number of Travel Days"></v-text-field>
            </v-col>
            <v-col cols="4">
              <v-text-field
                label="Return Airfare (per person)"
                prefix="$"
              ></v-text-field>
            </v-col>
          </v-row>
          <v-row class="px-5">
            <v-col cols="4">
              <v-text-field
                label="Accomodation per night (pp)"
                prefix="$"
              ></v-text-field>
            </v-col>
            <v-col cols="4">
              <v-text-field
                label="Hire Car per day (shared)"
                prefix="$"
              ></v-text-field>
            </v-col>
            <v-col cols="4">
              <v-text-field
                label="Meal Cost per day (per person)"
                prefix="$"
              ></v-text-field>
            </v-col>
          </v-row>
          <v-divider class="my-7"></v-divider>
          <v-row>
            <v-row class="mx-0 mb-0 pb-2 mb-0 pb-0">
              <v-col cols="12" class="ma-0 pb-0">
                <v-card-title class="mb-0 pb-0">Quantity</v-card-title>
              </v-col>
            </v-row>
            <v-row class="px-6 mt-0">
              <v-col
                class="px-2 mx-2"
                v-for="year in projectYears"
                :key="year.index"
              >
                <v-text-field :label="year.text"></v-text-field>
              </v-col>
            </v-row>
          </v-row>
          <v-row class="px-5">
            <v-col align="right" cols="12" class="pb-8">
              <v-btn
                @click="cancel()"
                color="primary"
                elevation="4"
                outlined
                large
                class="mr-3"
              >
                Cancel</v-btn
              >
              <v-btn
                @click="saveCost()"
                color="primary"
                elevation="4"
                outlined
                large
              >
                Save</v-btn
              >
            </v-col>
          </v-row>
        </v-card>
      </v-row>

      <!-- Simple (Single Value) Costs -->
      <v-row v-for="i in singleCostTypes" :id="i.id" :key="i.index">
        <template v-if="currentCostType == i.no">
          <v-card width="100%" class="mb-15" elevation="3">
            <v-card-title>{{ i.name }}</v-card-title>
            <v-row class="px-5">
              <v-col cols="8">
                <v-text-field
                  label="Description"
                  :value="currentDescription"
                  v-model="currentDescription"
                >
                </v-text-field>
              </v-col>
              <v-col cols="4">
                <v-text-field
                  label="Cost"
                  prefix="$"
                  :value="currentActualCost"
                  v-model="currentActualCost"
                >
                </v-text-field>
              </v-col>
            </v-row>
            <v-divider class="my-7"></v-divider>
            <v-row>
              <v-row class="mx-0 mb-0 pb-2 mb-0 pb-0">
                <v-col cols="12" class="ma-0 pb-0">
                  <v-card-title class="mb-0 pb-0">Quantity</v-card-title>
                </v-col>
              </v-row>
              <v-row class="px-6 mt-0">
                <v-col
                  class="px-2 mx-2"
                  v-for="year in projectYears"
                  :key="year.index"
                >
                  <v-text-field :label="year.text"></v-text-field>
                </v-col>
              </v-row>
            </v-row>
            <v-row class="px-5">
              <v-col align="right" cols="12" class="pb-8">
                <v-btn
                  @click="cancel()"
                  color="primary"
                  elevation="4"
                  outlined
                  large
                  class="mr-3"
                >
                  Cancel</v-btn
                >
                <v-btn
                  @click="saveCost()"
                  color="primary"
                  elevation="4"
                  outlined
                  large
                >
                  Save</v-btn
                >
              </v-col>
            </v-row>
          </v-card>
        </template>
      </v-row>

      <v-row>
        <v-col cols="12" class="ml-1">
          <v-text-field
            v-model="search"
            append-icon="mdi-magnify"
            label="Search"
            single-line
            hide-details
          >
          </v-text-field>
        </v-col>
        <v-col cols="12">
          <v-data-table
            :headers="headers"
            :items="costList"
            :search="search"
            @click:row="change"
          >
          </v-data-table>
        </v-col>
      </v-row>

      <v-row class="px-5">
        <v-col align="right" cols="12" class="pt-5">
          <v-btn
            @click="addingCost = true"
            color="primary"
            elevation="4"
            outlined
            large
            class="mr-3"
          >
            Add Cost</v-btn
          >
        </v-col>
      </v-row>
    </v-container>
  </v-app>
</template>
<script>
const axios = require("axios").default;
//import router from "../router";

export default {
  data() {
    return {
      search: "",
      // variable for selected cost (who know if this is needed)
      currentCostType: 0,
      currentDescription: "",
      currentActualCost: 0,
      currentCostIndex: -1,
      addingCost: false,
      newCostType: "",
      headers: [
        {
          text: "Type",
          align: "start",
          filterable: true,
          value: "type",
        },
        { text: "Description", value: "description" },
        { text: "In Kind Contribution", value: "in_kind" },
        { text: "Actual Cost", value: "actual_cost" },
      ],
      // Temporary hard-coded cost list
      costList: [
        {
          index: 0,
          no: 1,
          type: "Travel",
          description: "CI & RA Travel to Melbourne for conference",
          in_kind: "-",
          actual_cost: "3250.00",
        },
        {
          index: 1,
          no: 2,
          type: "Facility Hire",
          description: "Menzies - Zeiss LSM510",
          in_kind: "-",
          actual_cost: "6099.66",
        },
        {
          index: 2,
          no: 3,
          type: "Consumables",
          description: "6L of 10% HCL",
          in_kind: "-",
          actual_cost: "79.99",
        },
        {
          index: 3,
          no: 4,
          type: "Equipment Purchases",
          description: "Geological Picks",
          in_kind: "-",
          actual_cost: "335.50",
        },
      ],
      costListTemp: [],
      costListFixed: [],
      projectYears: [
        { text: "Year End " + "31/12/23" },
        { text: "Year End " + "31/12/24" },
        { text: "Year End " + "31/12/25" },
        { text: "Year End " + "31/12/26" },
      ],
      // Costs with only a single 'cost' entry
      singleCostTypes: [
        { name: "Facilities/Laboratories Hire", id: "Facility", no: 2 },
        { name: "Consumables", id: "Consumables", no: 3 },
        { name: "Equipment Purchases", id: "Equipment", no: 4 },
        { name: "External Contractor", id: "contractor", no: 5 },
        { name: "Other Costs", id: "Other", no: 6 },
        { name: "RHD Non-Stipend Costs", id: "RHD", no: 8 },
        { name: "Partner Organisation 1", id: "Partner1", no: 15 },
        { name: "Partner Organisation 2", id: "Partner2", no: 16 },
        { name: "Partner Organisation 3", id: "Partner3", no: 17 },
        { name: "Partner Organisation 4", id: "Partner4", no: 18 },
      ],
      // The currentCostType is a number 1-18, each representing:
      costNumbers: [
        { type: "Travel", no: 1 },
        { type: "Facility", no: 2 },
        { type: "Consumables", no: 3 },
        { type: "Equipment", no: 4 },
        { type: "Contractor", no: 5 },
        { type: "Other", no: 6 },
        { type: "Audit", no: 7 },
        { type: "RHD", no: 8 },
        { type: "AMC", no: 9 },
        { type: "TIA", no: 10 },
        { type: "IMAS", no: 11 },
        { type: "Menzies", no: 12 },
        { type: "Animal", no: 13 },
        { type: "CSL", no: 14 },
        { type: "Partner1", no: 15 },
        { type: "Partner2", no: 16 },
        { type: "Partner3", no: 17 },
        { type: "Partner4", no: 18 },
      ],
      costDropdownList: [
        "Travel",
        "Facilities/Laboratory hire",
        "Consumables",
        "Equipment purchases",
        "External contractor",
        "Other costs",
        "Audit fees",
        "RHD non-stipend costs",
        "AMC facility costs",
        "TIA facility costs",
        "IMAS facility costs",
        "Menzies facility costs",
        "Animal facility costs",
        "CSL Facility Costs",
        "Partner Organisation 1",
        "Partner Organisation 2",
        "Partner Organisation 3",
        "Partner Organisation 4",
      ],
    };
  },
  methods: {
    say(e) {
      alert(e);
    },
    load_project_cost() {
      axios
        .get(
          "http://10.36.241.204:8080/api/expenseList/" +
            this.$store.state.projectIndex
        )
        .then((resp) => {
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
      // display the cost info box
      this.currentCostType = item.no;
      this.currentDescription = item.description;
      this.currentActualCost = item.actual_cost;
      this.currentCostIndex = item.index;
    },
    cancel() {
      // hide all cost editing field
      this.currentCostType = 0;
      this.currentCostIndex = -1;
      this.addingCost = false;
    },
    saveCost() {
      if (this.currentCostIndex != -1) {
        // single value costs
        if (
          (this.currentCostType >= 2 && this.currentCostType <= 6) ||
          this.currentCostType == 8 ||
          (this.currentCostType >= 15 && this.currentCostType <= 18)
        ) {
          this.saveSingleCost();
        }
        if (this.currentCostType == 1) {
          // save travel cost
        }
      }
    },
    saveSingleCost() {
      // save description fields to cost array
      this.costList[this.currentCostIndex].description =
        this.currentDescription;

      // TODO: save actual cost (do calculations based on years?)
      this.costList[this.currentCostIndex].actual_cost = this.currentActualCost;

      // hide fields
      this.currentCostIndex = -1;
      this.currentCostType = 0;
    },
    addCost() {
      const type = (element) => element == this.newCostType;
      const newTypeNo = this.costDropdownList.findIndex(type) + 1;
      var newCost = {
        index: this.costList.length,
        no: newTypeNo,
        type: this.newCostType,
        description: "",
        in_kind: "-",
        actual_cost: "",
      };
      this.costList.push(newCost);
      this.addingCost = false;

      this.change(newCost);
    },
  },
  mounted() {
    if (this.$store.state.projectIndex == -1) {
      this.$router.push("/");
    }
    if (this.$store.state.projectIndex == 0) {
      this.costList = [];
    }
    this.load_project_cost();
    window.scrollTo(0, 0);
  },
};
</script>
