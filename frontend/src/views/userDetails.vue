<template>
  <div>
    <v-app>
      <v-container>
        <v-row class="pa-15">
          <h2 class="mx-auto">Project Details</h2>
        </v-row>
        <v-row>
          <v-col cols="6">
            <v-card outlined elevation="3" class="pt-3 pr-5">
              <v-row>
                <v-col cols="4">
                  <v-subheader>Project Name: </v-subheader>
                </v-col>
                <v-col cols="8">
                  <v-text-field v-model="name"></v-text-field>
                </v-col>
              </v-row>

              <v-row>
                <v-col cols="4">
                  <v-subheader>Lead Researcher: </v-subheader>
                </v-col>
                <v-col cols="8">
                  <v-text-field v-model="leadResearcherName"></v-text-field>
                </v-col>
              </v-row>

              <v-row>
                <v-col cols="4">
                  <v-subheader>Category: </v-subheader>
                </v-col>
                <v-col cols="8" class="pa-0">
                  <v-col class="d-flex">
                    <v-select
                      :items="category1"
                      label="Select Category"
                      v-model="category"
                    ></v-select>
                  </v-col>
                </v-col>
              </v-row>

              <v-row v-if="category == 'ONE'">
                <v-col cols="4">
                  <v-subheader>Category 1 Type: </v-subheader>
                </v-col>
                <v-col cols="8" class="pa-0">
                  <v-col class="d-flex">
                    <v-select
                      :items="category_1_Subtype"
                      label="Select Category"
                      v-model="category1Subtype"
                    ></v-select>
                  </v-col>
                </v-col>
              </v-row>

              <v-row v-if="category == 'CONSULTANCY'">
                <v-col cols="4">
                  <v-subheader>Profit Margin: </v-subheader>
                </v-col>
                <v-col cols="8">
                  <v-text-field
                    suffix="%"
                    v-model="profitMargin"
                  ></v-text-field>
                </v-col>
              </v-row>

              <v-row>
                <v-col cols="4">
                  <v-subheader>AMC/Menzies: </v-subheader>
                </v-col>
                <v-col cols="8" class="pa-0">
                  <v-col class="d-flex">
                    <v-select
                      :items="amc_menzies"
                      label="Select"
                      v-model="amcMenzies"
                    ></v-select>
                  </v-col>
                </v-col>
              </v-row>

              <v-row v-if="amcMenzies == 'AMC'">
                <v-col cols="4">
                  <v-subheader>AMC National Centre: </v-subheader>
                </v-col>
                <v-col cols="8" class="pa-0">
                  <v-col class="d-flex">
                    <v-select
                      :items="amc_national_centre"
                      label="Select"
                      v-model="amcNationalCentre"
                    ></v-select>
                  </v-col>
                </v-col>
              </v-row>
            </v-card>

            <v-card outlined elevation="3" class="pt-3 pr-3 mt-10">
              <v-row class="pl-3">
                <v-col md="6">
                  <v-menu
                    ref="menu"
                    v-model="menu"
                    :close-on-content-click="false"
                    :return-value.sync="date"
                    transition="scale-transition"
                    offset-y
                    min-width="auto"
                  >
                    <template v-slot:activator="{ on, attrs }">
                      <v-text-field
                        v-model="date"
                        label="Start Date"
                        prepend-icon="mdi-calendar"
                        readonly
                        v-bind="attrs"
                        v-on="on"
                      ></v-text-field>
                    </template>
                    <v-date-picker v-model="date" no-title scrollable>
                      <v-spacer></v-spacer>
                      <v-btn text color="primary" @click="menu = false">
                        Cancel
                      </v-btn>
                      <v-btn
                        text
                        color="primary"
                        @click="$refs.menu.save(date)"
                      >
                        OK
                      </v-btn>
                    </v-date-picker>
                  </v-menu>
                </v-col>
                <v-col md="6">
                  <v-menu
                    ref="menu2"
                    v-model="menu2"
                    :close-on-content-click="false"
                    :return-value.sync="date2"
                    transition="scale-transition"
                    offset-y
                    min-width="auto"
                  >
                    <template v-slot:activator="{ on, attrs }">
                      <v-text-field
                        v-model="date2"
                        label="End Date"
                        prepend-icon="mdi-calendar"
                        readonly
                        v-bind="attrs"
                        v-on="on"
                      ></v-text-field>
                    </template>
                    <v-date-picker v-model="date2" no-title scrollable>
                      <v-spacer></v-spacer>
                      <v-btn text color="primary" @click="menu2 = false">
                        Cancel
                      </v-btn>
                      <v-btn
                        text
                        color="primary"
                        @click="$refs.menu2.save(date2)"
                      >
                        OK
                      </v-btn>
                    </v-date-picker>
                  </v-menu>
                </v-col>
              </v-row>

              <v-row>
                <v-col cols="4">
                  <v-subheader>Year End: </v-subheader>
                </v-col>
                <v-col cols="8" class="pa-0">
                  <v-col class="d-flex">
                    <v-select
                      :items="year_end"
                      label="Select"
                      v-model="yearEnd"
                    ></v-select>
                  </v-col>
                </v-col>
              </v-row>
            </v-card>

            <v-card outlined elevation="3" class="pt-3 mt-10">
              <v-row>
                <v-col cols="8">
                  <v-subheader>UTAS Cash Contributions</v-subheader>
                </v-col>
                <v-col cols="4">
                  <v-col class="d-flex" sm="8">
                    <v-switch v-model="UTAS_Cash" inset></v-switch>
                  </v-col>
                </v-col>
                <v-col v-if="UTAS_Cash" cols="12">
                  <v-text-field
                    label="Amount"
                    outlined
                    class="mx-5 mt-0"
                    v-model="utasCashContribution"
                    prefix="$"
                  ></v-text-field>
                </v-col>
              </v-row>

              <v-row>
                <v-col cols="8">
                  <v-subheader>Partner Cash Contribution</v-subheader>
                </v-col>
                <v-col cols="4">
                  <v-col class="d-flex" sm="8">
                    <v-switch v-model="Partner_Cash" inset></v-switch>
                  </v-col>
                </v-col>
                <v-col v-if="Partner_Cash" cols="12">
                  <v-text-field
                    label="Amount"
                    outlined
                    class="mx-5 mt-0"
                    v-model="partnerCashContribution"
                    prefix="$"
                  ></v-text-field>
                </v-col>
              </v-row>

              <v-row>
                <v-col cols="8">
                  <v-subheader>Crowd Funding</v-subheader>
                </v-col>
                <v-col cols="4">
                  <v-col class="d-flex mb-0" sm="8">
                    <v-switch v-model="Crowd_Funding" inset></v-switch>
                  </v-col>
                </v-col>
                <v-col v-if="Crowd_Funding" cols="12" class="mt-0">
                  <v-select
                    :items="crowd_funding_provider"
                    label="Select"
                    outlined
                    class="mx-5 mt-0"
                    v-model="crowdFundingProvider"
                  ></v-select>
                </v-col>
              </v-row>
            </v-card>

            <v-card outlined elevation="3" class="pt-3 pr-5 mt-10">
              <v-card-title>Types of Activity</v-card-title>
              <v-row>
                <v-col cols="6">
                  <v-subheader>Applied Research </v-subheader>
                </v-col>
                <v-col cols="6">
                  <v-text-field
                    suffix="%"
                    v-model="appliedResearch"
                  ></v-text-field>
                </v-col>
              </v-row>
              <v-row>
                <v-col cols="6">
                  <v-subheader>Experimental Research </v-subheader>
                </v-col>
                <v-col cols="6">
                  <v-text-field
                    suffix="%"
                    v-model="experimentalDevelopment"
                  ></v-text-field>
                </v-col>
              </v-row>
              <v-row>
                <v-col cols="6">
                  <v-subheader>Strategic Basic </v-subheader>
                </v-col>
                <v-col cols="6">
                  <v-text-field
                    suffix="%"
                    v-model="strategicBasic"
                  ></v-text-field>
                </v-col>
              </v-row>
              <v-row>
                <v-col cols="6">
                  <v-subheader>Pure Basic </v-subheader>
                </v-col>
                <v-col cols="6">
                  <v-text-field suffix="%" v-model="pureBasic"></v-text-field>
                </v-col>
              </v-row>
            </v-card>
          </v-col>

          <v-col cols="6">
            <v-card outlined elevation="3">
              <v-card-title>Categories</v-card-title>
              <v-card-text>
                Donec egestas tellus sed velit imperdiet vehicula. Duis
                malesuada, dolor vitae fringilla euismod, lectus ligula congue
                est, in congue orci urna id sem. Suspendisse potenti. Sed mauris
                nisi, euismod quis justo vitae, luctus pulvinar libero.
                Suspendisse venenatis, quam sed bibendum ultricies, arcu risus
                dignissim arcu, quis blandit massa ante eget eros. Nunc eleifend
                lacus eu commodo sodales.
              </v-card-text>
              <v-card-text>
                Curabitur turpis turpis, maximus eget dapibus id, malesuada eget
                tellus. Integer at risus condimentum, eleifend est et, faucibus
                felis. Cras sed est sapien. Pellentesque habitant morbi
                tristique senectus et netus et malesuada fames ac turpis
                egestas.
              </v-card-text>
              <v-card-text>
                Suspendisse iaculis elit in dictum molestie. Praesent molestie
                porttitor tellus vel semper. Donec luctus tellus et sodales
                mollis. Integer in ultrices metus, a molestie mi. Aliquam eget
                malesuada leo. Etiam eget quam orci. Suspendisse euismod
                venenatis tempus. Ut ac eleifend dui.
              </v-card-text>
            </v-card>

            <v-card outlined elevation="3" class="pt-3 pr-3 mt-10">
              <v-row class="ma-3">
                <v-row>
                  <v-col cols="4">
                    <v-subheader>Entity: </v-subheader>
                  </v-col>
                  <v-col cols="8" class="pa-0">
                    <v-col class="d-flex">
                      <v-select
                        :items="research_institute"
                        label="Select Research Institute: "
                        v-model="entity"
                      ></v-select>
                    </v-col>
                  </v-col>
                </v-row>
              </v-row>

              <v-divider class="ml-3"></v-divider>

              <v-row class="mt-5 pl-3">
                <v-col cols="12" class="pb-0">
                  <v-subheader class="mt-0 pt-0">
                    Field of Research Codes:
                  </v-subheader>
                </v-col>
                <v-col cols="12" class="pt-0">
                  <template>
                    <v-col
                      class="d-flex"
                      sm="12"
                      v-for="i in research_codes_count"
                      v-bind:key="i"
                    >
                      <v-otp-input
                        length="6"
                        type="number"
                        id="'researchCodeInput' + i"
                        v-model="forHeader[i - 1]"
                      ></v-otp-input>
                      <v-text-field
                        suffix="%"
                        class="ml-2"
                        v-model="forCodes[Object.keys(forCodes)[i - 1]]"
                      ></v-text-field>
                    </v-col>
                  </template>
                  <v-col align="right" class="pt-0 mt-0">
                    <v-btn
                      class="mx-0"
                      v-if="research_codes_count < 3"
                      fab
                      x-small
                      @click="inc_research_codes()"
                    >
                      <v-icon dark> mdi-plus </v-icon>
                    </v-btn>
                    <v-btn
                      class="mx-1"
                      v-if="research_codes_count > 1"
                      fab
                      x-small
                      @click="dec_research_codes()"
                    >
                      <v-icon dark> mdi-minus </v-icon>
                    </v-btn>
                  </v-col>
                </v-col>
              </v-row>

              <v-row class="pl-3">
                <v-col cols="12" class="pb-0">
                  <v-subheader class="mt-0 pt-0">
                    Socio-Economic Objective Codes:
                  </v-subheader>
                </v-col>
                <v-col cols="12" class="pt-0">
                  <template>
                    <!--v-for="i in economic_codes_count" WENT IN ABOVE TEMPLATE??-->
                    <v-col
                      class="d-flex"
                      sm="12"
                      v-for="i in economic_codes_count"
                      v-bind:key="'economicCodeInput' + i"
                    >
                      <v-otp-input
                        length="6"
                        type="number"
                        :id="'ecoCodeInput' + i"
                        v-model="seoHeader[i - 1]"
                      ></v-otp-input>
                      <v-text-field
                        suffix="%"
                        class="ml-2"
                        v-model="seoCodes[Object.keys(seoCodes)[i - 1]]"
                      ></v-text-field>
                    </v-col>
                  </template>

                  <v-col align="right" class="pt-0 mt-0">
                    <v-btn
                      class="mx-0 mb-3"
                      v-if="economic_codes_count < 3"
                      fab
                      x-small
                      @click="inc_eco_codes()"
                    >
                      <v-icon dark> mdi-plus </v-icon>
                    </v-btn>
                    <v-btn
                      class="mx-1 mb-3"
                      v-if="economic_codes_count > 1"
                      fab
                      x-small
                      @click="dec_eco_codes()"
                    >
                      <v-icon dark> mdi-minus </v-icon>
                    </v-btn>
                  </v-col>
                </v-col>
              </v-row>
            </v-card>
          </v-col>
          <v-row class="mx-15 my-10">
            <v-btn
              color="primary"
              elevation="4"
              outlined
              x-large
              @click="update_details()"
              class="my-5"
              width="100%"
              >Submit
            </v-btn>
          </v-row>
        </v-row>
      </v-container>
    </v-app>
  </div>
</template>

<script>
const axios = require("axios").default;

export default {
  data: () => ({
    category1: ["ONE", "TWO", "THREE", "FOUR", "CONSULTANCY"],
    amc_menzies: ["NONE", "AMC", "MENZIES"],
    year_end: ["CALENDAR", "FINANCIAL"],
    research_institute: ["IMAS"],
    crowd_funding_provider: ["NONE"],
    category_1_Subtype: ["NONE", "ARC", "NHMRC/DECRA/DORA/DIA", "Other"],
    amc_national_centre: ["NONE", "NCMEH (OP:071001)", "NCPS (OP:071528)"],
    date: new Date(Date.now() - new Date().getTimezoneOffset() * 60000)
      .toISOString()
      .substr(0, 10),
    menu: false,
    date2: new Date(Date.now() - new Date().getTimezoneOffset() * 60000)
      .toISOString()
      .substr(0, 10),
    menu2: false,
    UTAS_Cash: false,
    Partner_Cash: false,
    Crowd_Funding: false,
    research_codes_count: 1,
    economic_codes_count: 1,
    name: "",
    decription: "",
    leadResearcherName: "",
    leadResearcherOrg: "",
    category: "",
    category1Subtype: "",
    amcMenzies: "",
    amcNationalCentre: "",
    profitMargin: 0,
    startDate: Date,
    endDate: Date,
    yearEnd: "",
    utasCashContribution: 0,
    partnerCashContribution: 0,
    crowdFundingProvider: "",
    entity: "",
    forCodes: 0,
    seoCodes: 0,
    appliedResearch: 0,
    experimentalDevelopment: 0,
    strategicBasic: 0,
    pureBasic: 0,
    test: ["123456", "122222"],
    projectIndexUpdate: 0,
    forHeader: [],
    seoHeader: [],
  }),
  methods: {
    say(e) {
      alert(e);
    },
    inc_research_codes() {
      if (this.research_codes_count < 3) {
        this.research_codes_count++;
      }
    },
    dec_research_codes() {
      console.log(this.forHeader.length);
      console.log(this.research_codes_count);
      if (
        this.research_codes_count > 1 &&
        this.forHeader.length == this.research_codes_count
      ) {
        this.research_codes_count--;
        delete this.forCodes[
          Object.keys(this.forCodes)[this.research_codes_count]
        ];
        this.forHeader.pop();
      } else {
        this.research_codes_count--;
      }
    },
    inc_eco_codes() {
      if (this.economic_codes_count < 3) {
        this.economic_codes_count++;
      }
    },
    dec_eco_codes() {
      if (
        this.economic_codes_count > 1 &&
        this.seoHeader.length == this.economic_codes_count
      ) {
        this.economic_codes_count--;
        delete this.seoCodes[
          Object.keys(this.seoCodes)[this.economic_codes_count]
        ];
        this.seoHeader.pop();
      } else {
        this.economic_codes_count--;
      }
    },
    load_projects_list() {
      if (this.$store.state.projectIndex == -1) {
        return;
      }
      var errorData = { status: 200 };
      axios
        .get(
          "http://10.36.241.204:8080/api/projects/" + this.projectIndexUpdate
        )
        .then((resp) => {
          this.connectError = 0;
          this.name = resp.data.name;
          this.decription = resp.data.description;
          this.leadResearcherName = resp.data.leadResearcherName;
          this.leadResearcherOrg = resp.data.leadResearcherOrg;
          this.category = resp.data.category;
          this.category1Subtype = resp.data.category1Subtype;
          this.amcMenzies = resp.data.amcMenzies;
          this.amcNationalCentre = resp.data.amcNationalCentre;
          this.profitMargin = resp.data.profitMargin;
          this.startDate = resp.data.startDate;
          this.date = resp.data.startDate;
          this.endDate = resp.data.endDate;
          this.date2 = resp.data.endDate;
          this.yearEnd = resp.data.yearEnd;
          this.utasCashContribution = resp.data.utasCashContribution;
          if (this.utasCashContribution > 0) {
            this.UTAS_Cash = true;
          }
          this.partnerCashContribution = resp.data.partnerCashContribution;
          if (this.partnerCashContribution > 0) {
            this.Partner_Cash = true;
          }
          this.crowdFundingProvider = resp.data.crowdFundingProvider;
          if (this.crowdFundingProvider == "?") {
            this.Crowd_Funding = true;
          }
          this.entity = resp.data.entity;
          this.forCodes = resp.data.forCodes;
          for (
            var index = 0;
            index < Object.keys(this.forCodes).length;
            index++
          ) {
            this.forHeader.push(Object.keys(this.forCodes)[index]);
          }
          this.research_codes_count = Object.keys(this.forCodes).length;
          this.seoCodes = resp.data.seoCodes;
          for (index = 0; index < Object.keys(this.seoCodes).length; index++) {
            this.seoHeader.push(Object.keys(this.seoCodes)[index]);
          }
          this.economic_codes_count = Object.keys(this.seoCodes).length;
          this.appliedResearch = resp.data.appliedResearch;
          this.experimentalDevelopment = resp.data.experimentalDevelopment;
          this.strategicBasic = resp.data.strategicBasic;
          this.pureBasic = resp.data.pureBasic;
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
    update_details() {
      var fcodes = {};
      var ecodes = {};
      for (var index = 0; index < Object.keys(this.forCodes).length; index++) {
        fcodes[this.forHeader[index]] =
          this.forCodes[Object.keys(this.forCodes)[index]];
      }
      for (index = 0; index < Object.keys(this.seoCodes).length; index++) {
        ecodes[this.seoHeader[index]] =
          this.seoCodes[Object.keys(this.seoCodes)[index]];
      }
      axios.put(
        "http://10.36.241.204:8080/api/projects/" + this.projectIndexUpdate,
        {
          name: this.name,
          description: this.decription,
          leadResearcherName: this.leadResearcherName,
          leadResearcherOrg: this.leadResearcherOrg,
          category: this.category,
          category1Subtype: this.category1Subtype,
          amcMenzies: this.amcMenzies,
          amcNationalCentre: this.amcNationalCentre,
          profitMargin: this.profitMargin,
          startDate: this.date,
          endDate: this.date2,
          yearEnd: this.yearEnd,
          utasCashContribution: this.utasCashContribution,
          partnerCashContribution: this.partnerCashContribution,
          crowdFundingProvider: this.crowdFundingProvider,
          entity: this.entity,
          forCodes: fcodes,
          seoCodes: ecodes,
          appliedResearch: this.appliedResearch,
          experimentalDevelopment: this.experimentalDevelopment,
          strategicBasic: this.strategicBasic,
          pureBasic: this.pureBasic,
        }
      );
      window.scrollTo(0, 0);
      this.$router.push("/researcher");
    },
  },
  mounted() {
    if (this.$store.state.projectIndex == -1) {
      this.$router.push("/");
    }
    if (this.$store.state.projectIndex != 0) {
      this.projectIndexUpdate = this.$store.state.projectIndex;
      this.load_projects_list();
    }
    window.scrollTo(0, 0);
  },
};
</script>
