<template>
  <div>
    <v-app>
      <v-container>
        <v-row class="pa-15">
          <h2 class="mx-auto">Project Summary</h2>
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
          :items="summaryListFixed"
          :search="search"
        >
        </v-data-table>
        <v-row class="my-10 mr-10">
          <table>
            <tr>
              <td style="border-right: 1px solid grey">
                <table>
                  <tr>
                    <th>Profit Margin:</th>
                    <td>{{ this.profitMargin }}%</td>
                  </tr>
                </table>
              </td>
              <td style="border-right: 1px solid grey">
                <table>
                  <tr>
                    <th>UTAS Cash:</th>
                    <td>${{ this.utasCash }}</td>
                  </tr>
                </table>
              </td>
              <td>
                <table>
                  <tr>
                    <th>Partner Cash:</th>
                    <td>${{ this.partnerCash }}</td>
                  </tr>
                </table>
              </td>
            </tr>
          </table>
        </v-row>
      </v-container>
    </v-app>
  </div>
</template>
<script>
const axios = require("axios").default;

export default {
  data() {
    return {
      search: "",
      errorMessage: "",
      headers: [
        {
          text: "Type",
          align: "start",
          filterable: true,
          value: "type",
        },
        { text: "Name", value: "name" },
        { text: "Actual Cost", value: "actualCost" },
        { text: "In Kind Percent", value: "inKindPercent" },
      ],
      summaryList: [],
      summaryListFixed: [],
      expenses: [],
      labour: [],
      partnerCash: 0,
      profitMargin: 0,
      utasCash: 0,
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
    async load_summary() {
      var errorData = { status: 200 };
      await axios
        .get(
          "http://10.36.241.204:8080/api/summary/" +
            this.$store.state.projectIndex
        )
        .then((resp) => {
          this.connectError = 0;
          this.summaryList = resp.data;
          var obj;
          // for (var i = 0; i < this.summaryList.labour.length; i++) {
          // for (var j = 0; j < resp.data.labour.nonCasual.length; j++) {
          //   obj = {
          //     contract: "Non Casual",
          //     name: resp.data.labour.nonCasual[j].name,
          //     actualCost: resp.data.labour.nonCasual[j].actualCost,
          //     inKindPercent: resp.data.labour.nonCasual[j].inKindPercent,
          //   };
          //   this.labour.push(obj);
          // }
          obj = {
            type: "Labour",
            name: resp.data.labour.nonCasual.name,
            actualCost: resp.data.labour.nonCasual.actualCost,
            inKindPercent: resp.data.labour.nonCasual.inKindPercent,
          };
          this.summaryListFixed.push(obj);
          obj = {
            type: "Labour",
            name: resp.data.labour.casual.name,
            actualCost: resp.data.labour.casual.actualCost,
            inKindPercent: resp.data.labour.casual.inKindPercent,
          };
          this.summaryListFixed.push(obj);
          obj = {
            type: "Labour",
            name: resp.data.labour.rhd.name,
            actualCost: resp.data.labour.rhd.actualCost,
            inKindPercent: resp.data.labour.rhd.inKindPercent,
          };
          this.summaryListFixed.push(obj);
          obj = {
            type: "Expense",
            name: resp.data.expenses.auditFees.name,
            actualCost: resp.data.expenses.auditFees.actualCost,
            inKindPercent: resp.data.expenses.auditFees.inKindPercent,
          };
          this.summaryListFixed.push(obj);
          obj = {
            type: "Expense",
            name: resp.data.expenses.consumables.name,
            actualCost: resp.data.expenses.consumables.actualCost,
            inKindPercent: resp.data.expenses.consumables.inKindPercent,
          };
          this.summaryListFixed.push(obj);
          obj = {
            type: "Expense",
            name: resp.data.expenses.externalContractor.name,
            actualCost: resp.data.expenses.externalContractor.actualCost,
            inKindPercent: resp.data.expenses.externalContractor.inKindPercent,
          };
          this.summaryListFixed.push(obj);
          obj = {
            type: "Expense",
            name: resp.data.expenses.facilityCosts.name,
            actualCost: resp.data.expenses.facilityCosts.actualCost,
            inKindPercent: resp.data.expenses.facilityCosts.inKindPercent,
          };
          this.summaryListFixed.push(obj);
          obj = {
            type: "Expense",
            name: resp.data.expenses.laboratoryHire.name,
            actualCost: resp.data.expenses.laboratoryHire.actualCost,
            inKindPercent: resp.data.expenses.laboratoryHire.inKindPercent,
          };
          this.summaryListFixed.push(obj);
          obj = {
            type: "Expense",
            name: resp.data.expenses.otherCosts.name,
            actualCost: resp.data.expenses.otherCosts.actualCost,
            inKindPercent: resp.data.expenses.otherCosts.inKindPercent,
          };
          this.summaryListFixed.push(obj);
          obj = {
            type: "Expense",
            name: resp.data.expenses.partnerOrganisation.name,
            actualCost: resp.data.expenses.partnerOrganisation.actualCost,
            inKindPercent: resp.data.expenses.partnerOrganisation.inKindPercent,
          };
          this.summaryListFixed.push(obj);
          obj = {
            type: "Expense",
            name: resp.data.expenses.rhdNonStripendCosts.name,
            actualCost: resp.data.expenses.rhdNonStripendCosts.actualCost,
            inKindPercent: resp.data.expenses.rhdNonStripendCosts.inKindPercent,
          };
          this.summaryListFixed.push(obj);
          obj = {
            type: "Expense",
            name: resp.data.expenses.travel.name,
            actualCost: resp.data.expenses.travel.actualCost,
            inKindPercent: resp.data.expenses.travel.inKindPercent,
          };
          this.summaryListFixed.push(obj);
          this.partnerCash = resp.data.partnerCash;
          this.profitMargin = resp.data.profitMargin;
          this.utasCash = resp.data.utasCash;
          console.log(this.summaryList);
          console.log(this.labour);
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
  },
  mounted() {
    if (this.$store.state.projectIndex == -1) {
      this.$router.push("/");
    }
    if (this.$store.state.projectIndex == 0) {
      this.costList = [];
    }
    this.load_summary();
    window.scrollTo(0, 0);
  },
};
</script>
