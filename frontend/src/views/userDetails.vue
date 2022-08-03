<template>
  <div>
    <v-app>
      <v-container>
        <v-row>
          <v-col cols="2">
            <v-subheader>Project Name: </v-subheader>
          </v-col>
          <v-col cols="2">
            <v-text-field></v-text-field>
          </v-col>
        </v-row>

        <v-row>
          <v-col cols="2">
            <v-subheader>Lead Researcher: </v-subheader>
          </v-col>
          <v-col cols="2">
            <v-text-field></v-text-field>
          </v-col>
        </v-row>

        <v-row>
          <v-col cols="2">
            <v-subheader>Category: </v-subheader>
          </v-col>
          <v-col cols="7">
            <v-col class="d-flex" sm="3">
              <v-select :items="category" label="Select Category"></v-select>
            </v-col>
          </v-col>
        </v-row>

        <v-row>
          <v-col cols="2">
            <v-subheader>AMC/Menzies: </v-subheader>
          </v-col>
          <v-col cols="7">
            <v-col class="d-flex" sm="3">
              <v-select :items="amc_menzies" label="Select"></v-select>
            </v-col>
          </v-col>
        </v-row>

        <v-row>
          <v-col md="2">
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
                <v-btn text color="primary" @click="$refs.menu.save(date)">
                  OK
                </v-btn>
              </v-date-picker>
            </v-menu>
          </v-col>
          <v-col md="2">
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
                <v-btn text color="primary" @click="$refs.menu2.save(date2)">
                  OK
                </v-btn>
              </v-date-picker>
            </v-menu>
          </v-col>
        </v-row>

        <v-row>
          <v-col cols="1">
            <v-subheader>Year End: </v-subheader>
          </v-col>
          <v-col cols="7">
            <v-col class="d-flex" sm="3">
              <v-select :items="year_end" label="Select"></v-select>
            </v-col>
          </v-col>
        </v-row>

        <v-row>
          <v-col cols="2">
            <v-subheader>UTAS Cash Contributions: </v-subheader>
          </v-col>
          <v-col cols="15">
            <v-col class="d-flex" sm="8">
              <v-switch v-model="UTAS_Cash" inset></v-switch>
              <v-row v-if="UTAS_Cash">
                <v-col cols="4">
                  <v-text-field label="Amount" outlined></v-text-field>
                </v-col>
              </v-row>
            </v-col>
          </v-col>
        </v-row>

        <v-row>
          <v-col cols="2">
            <v-subheader>Partner Cash Contribution: </v-subheader>
          </v-col>
          <v-col cols="15">
            <v-col class="d-flex" sm="8">
              <v-switch v-model="Partner_Cash" inset></v-switch>
              <v-row v-if="Partner_Cash">
                <v-col cols="4">
                  <v-text-field label="Amount" outlined></v-text-field>
                </v-col>
              </v-row>
            </v-col>
          </v-col>
        </v-row>

        <v-row>
          <v-col cols="2">
            <v-subheader>Crowd Funding: </v-subheader>
          </v-col>
          <v-col cols="15">
            <v-col class="d-flex" sm="8">
              <v-switch v-model="Crowd_Funding" inset></v-switch>
              <v-row v-if="Crowd_Funding">
                <v-col cols="4">
                  <v-text-field label="Provider" outlined></v-text-field>
                </v-col>
              </v-row>
            </v-col>
          </v-col>
        </v-row>

        <v-row>
          <v-col cols="1">
            <v-subheader>Entity: </v-subheader>
          </v-col>
          <v-col cols="7">
            <v-col class="d-flex" sm="3">
              <v-select
                :items="research_institute"
                label="Select Research Institute: "
              ></v-select>
            </v-col>
          </v-col>
        </v-row>

        <v-row>
          <v-col cols="1">
            <v-subheader>Field of Research Codes: </v-subheader>
          </v-col>
          <v-col cols="3">
            <v-col class="d-flex" sm="12">
              <v-otp-input length="6" type="number"></v-otp-input>
              <v-text-field suffix="%"></v-text-field>
            </v-col>
            <v-btn class="mx-0" fab x-small>
              <v-icon dark> mdi-plus </v-icon>
            </v-btn>
          </v-col>
        </v-row>

        <v-row>
          <v-col cols="1">
            <v-subheader>Socio-Economic Objective Codes: </v-subheader>
          </v-col>
          <v-col cols="3">
            <v-col class="d-flex" sm="12">
              <v-otp-input length="6" type="number"></v-otp-input>
              <v-text-field suffix="%"></v-text-field>
            </v-col>
            <v-btn class="mx-0" fab x-small>
              <v-icon dark> mdi-plus </v-icon>
            </v-btn>
          </v-col>
        </v-row>

        <h4>Types of Activity:</h4>
        <v-row>
          <v-col cols="2">
            <v-subheader>Applied Research </v-subheader>
          </v-col>
          <v-col cols="1">
            <v-text-field suffix="%"></v-text-field>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="2">
            <v-subheader>Experimental Research </v-subheader>
          </v-col>
          <v-col cols="1">
            <v-text-field suffix="%"></v-text-field>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="2">
            <v-subheader>Strategic Basic </v-subheader>
          </v-col>
          <v-col cols="1">
            <v-text-field suffix="%"></v-text-field>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="2">
            <v-subheader>Pure Basic </v-subheader>
          </v-col>
          <v-col cols="1">
            <v-text-field suffix="%"></v-text-field>
          </v-col>
        </v-row>
      </v-container>
    </v-app>
  </div>
</template>

<script>
export default {
  data: () => ({
    category: ["Category1", "Category2"],
    amc_menzies: ["Yes", "No"],
    year_end: ["Calender", "??"],
    research_institute: ["??"],
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
  }),
};
</script>
