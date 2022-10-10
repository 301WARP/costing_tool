<template>
  <v-app>
    <v-container>
      <!-- <v-subheader>Initial</v-subheader>
      <v-switch v-model="initial" inset></v-switch>
      <v-subheader>Casual</v-subheader>
      <v-switch v-model="casual" inset></v-switch>
      <v-subheader>RHD</v-subheader>
      <v-switch v-model="rhd" inset></v-switch> -->
      <v-row class="pa-15">
        <h2 class="mx-auto">Direct Labour Costs</h2>
      </v-row>

      <v-row>
        <v-card
          elevation="5"
          outlined
          width="100%"
          class="mb-10 pt-7 pr-10 pb-5"
        >
          <v-row>
            <v-col cols="4">
              <v-row>
                <v-col cols="4">
                  <v-subheader>Title: </v-subheader>
                </v-col>
                <v-col cols="8" class="pa-0">
                  <v-col class="pa-0">
                    <v-select
                      :items="title"
                      label="Select Title"
                      v-model="title_input"
                    ></v-select>
                  </v-col>
                </v-col>
              </v-row>
            </v-col>
            <v-col cols="4">
              <v-row>
                <v-col cols="4">
                  <v-subheader>Family Name: </v-subheader>
                </v-col>
                <v-col cols="8" class="pa-0">
                  <v-text-field v-model="family_name"></v-text-field>
                </v-col>
              </v-row>
            </v-col>
            <v-col cols="4">
              <v-row>
                <v-col cols="4">
                  <v-subheader>Given Names: </v-subheader>
                </v-col>
                <v-col cols="8" class="pa-0">
                  <v-text-field v-model="given_name"></v-text-field>
                </v-col>
              </v-row>
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="6">
              <v-row>
                <v-col cols="3">
                  <v-subheader>Role: </v-subheader>
                </v-col>
                <v-col cols="9" class="pa-0">
                  <v-text-field v-model="role_input"></v-text-field>
                </v-col>
              </v-row>
            </v-col>
            <v-col cols="6">
              <v-row>
                <v-col cols="4">
                  <v-subheader>Contract: </v-subheader>
                </v-col>
                <v-col cols="8" class="pa-0">
                  <v-row>
                    <v-col class="d-flex">
                      <v-select
                        :items="contract"
                        label="Select Contract"
                        @change="checkContract($event)"
                        v-model="contract_input"
                      ></v-select>
                    </v-col>
                  </v-row>
                </v-col>
              </v-row>
            </v-col>
          </v-row>
          <v-row></v-row>
        </v-card>
      </v-row>

      <v-row v-if="full_time">
        <v-card
          elevation="5"
          outlined
          width="100%"
          class="mb-10 pt-3 pr-10 pb-5"
        >
          <v-card-title class="ml-2">
            UTAS non-casual staff
            <v-tooltip top>
              <template v-slot:activator="{ on, attrs }">
                <v-icon v-bind="attrs" v-on="on" class="ml-2">
                  mdi-information
                </v-icon>
              </template>
              <span>
                Academic Staff, Honorary Researchers, Technical, Administration
                Staff and Research Assistants
              </span>
            </v-tooltip>
          </v-card-title>
          <v-row class="pl-3">
            <v-col cols="4">
              <v-row>
                <!-- <v-col cols="4">
                  <v-subheader>Staff Type: </v-subheader>
                </v-col> -->
                <v-col cols="12">
                  <v-col class="d-flex">
                    <v-select
                      :items="staff_type"
                      label="Select Staff Type"
                      v-model="staff_type_full"
                    ></v-select>
                  </v-col>
                </v-col>
              </v-row>
            </v-col>
            <v-col cols="4">
              <v-row>
                <!-- <v-col cols="4">
                  <v-subheader>Classification: </v-subheader>
                </v-col> -->
                <v-col cols="12">
                  <v-col class="d-flex">
                    <v-select
                      :items="classification"
                      label="Select Classification"
                      v-model="classification_full"
                    ></v-select>
                  </v-col>
                </v-col>
              </v-row>
            </v-col>
            <v-col cols="4">
              <v-row>
                <!-- <v-col cols="4">
                  <v-subheader>Step: </v-subheader>
                </v-col> -->
                <v-col cols="12">
                  <v-col class="d-flex">
                    <v-select
                      :items="step"
                      label="Select Step"
                      v-model="step_full"
                    ></v-select>
                  </v-col>
                </v-col>
              </v-row>
            </v-col>
          </v-row>

          <v-row>
            <v-col cols="4" class="pl-8">
              <v-row>
                <v-col cols="4">
                  <v-subheader>Starting Salary: </v-subheader>
                </v-col>
                <v-col cols="8">
                  <v-text-field v-model="start_salary_full" prefix="$">
                  </v-text-field>
                </v-col>
              </v-row>
              <v-row>
                <v-col cols="4">
                  <v-subheader>Wage Adjustment: </v-subheader>
                </v-col>
                <v-col cols="8">
                  <v-text-field
                    v-model="wage_adj_full"
                    class="pl-3"
                    prefix="$"
                  ></v-text-field>
                </v-col>
              </v-row>
              <v-row>
                <v-col cols="5">
                  <v-subheader>Salary on Cost Rate: </v-subheader>
                </v-col>
                <v-col cols="7">
                  <v-col class="d-flex mx-0 px-0">
                    <v-select
                      :items="salary_cost_rate"
                      v-model="salary_rate_full"
                    >
                    </v-select>
                  </v-col>
                </v-col>
              </v-row>
              <v-row>
                <v-col cols="4">
                  <v-subheader>In Kind Contribution: </v-subheader>
                </v-col>
                <v-col cols="8">
                  <v-text-field v-model="in_kind_full" suffix="%" class="pl-5">
                  </v-text-field>
                </v-col>
              </v-row>
            </v-col>
            <v-col cols="8">
              <v-row class="mt-5 mb-5">
                <v-data-table
                  :headers="fte_header"
                  :items="years"
                  sort-by="year"
                  class="elevation-3 mx-auto my-auto"
                  :style="{ width: '500px' }"
                >
                  <template v-slot:top>
                    <v-toolbar flat>
                      <v-toolbar-title>FTE</v-toolbar-title>
                      <v-divider class="mx-4" inset vertical></v-divider>
                      <v-spacer></v-spacer>
                      <v-dialog v-model="dialog" max-width="500px">
                        <template v-slot:activator="{ on, attrs }">
                          <v-btn
                            color="primary"
                            dark
                            class="mb-2"
                            v-bind="attrs"
                            v-on="on"
                          >
                            New Item
                          </v-btn>
                        </template>
                        <v-card>
                          <v-card-title>
                            <span class="text-h5">{{ formTitle }}</span>
                          </v-card-title>

                          <v-card-text>
                            <v-container>
                              <v-row>
                                <v-col cols="12" sm="6" md="4">
                                  <v-text-field
                                    v-model="editedItem.year"
                                    label="Year"
                                  ></v-text-field>
                                </v-col>
                                <v-col cols="12" sm="6" md="4">
                                  <v-text-field
                                    v-model="editedItem.fte"
                                    label="FTE%"
                                  ></v-text-field>
                                </v-col>
                              </v-row>
                            </v-container>
                          </v-card-text>

                          <v-card-actions>
                            <v-spacer></v-spacer>
                            <v-btn color="blue darken-1" text @click="close">
                              Cancel
                            </v-btn>
                            <v-btn color="blue darken-1" text @click="save">
                              Save
                            </v-btn>
                          </v-card-actions>
                        </v-card>
                      </v-dialog>
                      <v-dialog v-model="dialogDelete" max-width="500px">
                        <v-card>
                          <v-card-title class="text-h5"
                            >Are you sure you want to delete this
                            item?</v-card-title
                          >
                          <v-card-actions>
                            <v-spacer></v-spacer>
                            <v-btn
                              color="blue darken-1"
                              text
                              @click="closeDelete"
                              >Cancel</v-btn
                            >
                            <v-btn
                              color="blue darken-1"
                              text
                              @click="deleteItemConfirm"
                              >OK</v-btn
                            >
                            <v-spacer></v-spacer>
                          </v-card-actions>
                        </v-card>
                      </v-dialog>
                    </v-toolbar>
                  </template>
                  <template v-slot:[`item.actions`]="{ item }">
                    <v-icon small class="mr-2" @click="editItem(item)">
                      mdi-pencil
                    </v-icon>
                    <v-icon small @click="deleteItem(item)">
                      mdi-delete
                    </v-icon>
                  </template>
                </v-data-table>
              </v-row>
              <v-row>
                <v-col align="right">
                  <v-btn
                    color="primary"
                    elevation="4"
                    outlined
                    x-large
                    @click="full_time = false"
                    class="mr-5"
                    >Close
                  </v-btn>
                  <v-btn
                    color="primary"
                    elevation="4"
                    outlined
                    x-large
                    @click="submit('NON_CASUAL')"
                    >Submit
                  </v-btn>
                </v-col>
              </v-row>
            </v-col>
          </v-row>
        </v-card>
      </v-row>

      <v-row v-if="casual">
        <v-card
          elevation="5"
          outlined
          width="100%"
          class="mb-10 pt-3 pr-10 pb-5"
        >
          <v-card-title class="ml-2">
            Casual Staff
            <v-tooltip top>
              <template v-slot:activator="{ on, attrs }">
                <v-icon v-bind="attrs" v-on="on" class="ml-2">
                  mdi-information
                </v-icon>
              </template>
              <span>UTAS staff paid an hourly rate</span>
            </v-tooltip>
          </v-card-title>
          <v-row class="pl-3">
            <v-col cols="4">
              <v-row>
                <v-col cols="12">
                  <v-col class="d-flex">
                    <v-select
                      :items="staff_type"
                      label="Select Staff Type"
                      v-model="staff_type_casual"
                    ></v-select>
                  </v-col>
                </v-col>
              </v-row>
            </v-col>
            <v-col cols="4">
              <v-row>
                <v-col cols="12">
                  <v-col class="d-flex">
                    <v-select
                      :items="classification"
                      label="Select Classification"
                      v-model="classification_casual"
                    ></v-select>
                  </v-col>
                </v-col>
              </v-row>
            </v-col>
            <v-col cols="4">
              <v-row>
                <v-col cols="12">
                  <v-col class="d-flex">
                    <v-select
                      :items="pay_code"
                      label="Select Pay Code"
                      v-model="pay_code_casual"
                    ></v-select>
                  </v-col>
                </v-col>
              </v-row>
            </v-col>
          </v-row>

          <v-row>
            <v-col cols="4" class="pl-8">
              <v-row>
                <v-col cols="4">
                  <v-subheader>Starting Salary: </v-subheader>
                </v-col>
                <v-col cols="8">
                  <v-text-field v-model="start_salary_casual" prefix="$">
                  </v-text-field>
                </v-col>
              </v-row>
              <v-row>
                <v-col cols="4">
                  <v-subheader>Wage Adjustment: </v-subheader>
                </v-col>
                <v-col cols="8">
                  <v-text-field
                    v-model="wage_adj_casual"
                    class="pl-3"
                    prefix="$"
                  ></v-text-field>
                </v-col>
              </v-row>
              <v-row>
                <v-col cols="5">
                  <v-subheader>Salary on Cost Rate: </v-subheader>
                </v-col>
                <v-col cols="7">
                  <v-col class="d-flex mx-0 px-0">
                    <v-select
                      :items="salary_cost_rate"
                      label="Select Salary on Cost Rate"
                      v-model="salary_rate_casual"
                    >
                    </v-select>
                  </v-col>
                </v-col>
              </v-row>
              <v-row>
                <v-col cols="4">
                  <v-subheader>In Kind Contribution: </v-subheader>
                </v-col>
                <v-col cols="8">
                  <v-text-field
                    v-model="in_kind_casual"
                    suffix="%"
                    class="pl-5"
                  >
                  </v-text-field>
                </v-col>
              </v-row>
            </v-col>
            <v-col cols="8">
              <v-row class="mt-5 mb-5">
                <v-data-table
                  :headers="fte_header"
                  :items="years"
                  sort-by="year"
                  class="elevation-3 mx-auto my-auto"
                  :style="{ width: '500px' }"
                >
                  <template v-slot:top>
                    <v-toolbar flat>
                      <v-toolbar-title>FTE</v-toolbar-title>
                      <v-divider class="mx-4" inset vertical></v-divider>
                      <v-spacer></v-spacer>
                      <v-dialog v-model="dialog" max-width="500px">
                        <template v-slot:activator="{ on, attrs }">
                          <v-btn
                            color="primary"
                            dark
                            class="mb-2"
                            v-bind="attrs"
                            v-on="on"
                          >
                            New Item
                          </v-btn>
                        </template>
                        <v-card>
                          <v-card-title>
                            <span class="text-h5">{{ formTitle }}</span>
                          </v-card-title>

                          <v-card-text>
                            <v-container>
                              <v-row>
                                <v-col cols="12" sm="6" md="4">
                                  <v-text-field
                                    v-model="editedItem.year"
                                    label="Year"
                                  ></v-text-field>
                                </v-col>
                                <v-col cols="12" sm="6" md="4">
                                  <v-text-field
                                    v-model="editedItem.fte"
                                    label="FTE%"
                                  ></v-text-field>
                                </v-col>
                              </v-row>
                            </v-container>
                          </v-card-text>

                          <v-card-actions>
                            <v-spacer></v-spacer>
                            <v-btn color="blue darken-1" text @click="close">
                              Cancel
                            </v-btn>
                            <v-btn color="blue darken-1" text @click="save">
                              Save
                            </v-btn>
                          </v-card-actions>
                        </v-card>
                      </v-dialog>
                      <v-dialog v-model="dialogDelete" max-width="500px">
                        <v-card>
                          <v-card-title class="text-h5">
                            Are you sure you want to delete this item?
                          </v-card-title>
                          <v-card-actions>
                            <v-spacer></v-spacer>
                            <v-btn
                              color="blue darken-1"
                              text
                              @click="closeDelete"
                            >
                              Cancel
                            </v-btn>
                            <v-btn
                              color="blue darken-1"
                              text
                              @click="deleteItemConfirm"
                            >
                              OK
                            </v-btn>
                            <v-spacer></v-spacer>
                          </v-card-actions>
                        </v-card>
                      </v-dialog>
                    </v-toolbar>
                  </template>
                  <template v-slot:[`item.actions`]="{ item }">
                    <v-icon small class="mr-2" @click="editItem(item)">
                      mdi-pencil
                    </v-icon>
                    <v-icon small @click="deleteItem(item)">
                      mdi-delete
                    </v-icon>
                  </template>
                </v-data-table>
              </v-row>
              <v-row>
                <v-col align="right">
                  <v-btn
                    color="primary"
                    elevation="4"
                    outlined
                    x-large
                    @click="casual = false"
                    class="mr-5"
                    >Close
                  </v-btn>
                  <v-btn
                    color="primary"
                    elevation="4"
                    outlined
                    x-large
                    @click="submit('CASUAL')"
                    >Submit
                  </v-btn>
                </v-col>
              </v-row>
            </v-col>
          </v-row>
        </v-card>
      </v-row>

      <v-row v-if="rhd">
        <v-card
          elevation="5"
          outlined
          width="100%"
          class="mb-10 pt-3 pr-10 pb-5"
        >
          <v-card-title class="ml-2">
            Research Higher Degree (RHD) Stipends
          </v-card-title>

          <v-row>
            <v-col cols="4" class="pl-8">
              <v-row>
                <v-col cols="12">
                  <v-col class="d-flex">
                    <v-select
                      :items="classification"
                      label="Select Classification"
                      v-model="classification_rhd"
                    ></v-select>
                  </v-col>
                </v-col>
              </v-row>
              <v-row>
                <v-col cols="4">
                  <v-subheader>Annual Salary: </v-subheader>
                </v-col>
                <v-col cols="8">
                  <v-text-field v-model="annual_salary_rhd" prefix="$">
                  </v-text-field>
                </v-col>
              </v-row>
              <v-row>
                <v-col cols="4">
                  <v-subheader>In Kind Contribution: </v-subheader>
                </v-col>
                <v-col cols="8">
                  <v-text-field v-model="in_kind_rhd" suffix="%" class="pl-5">
                  </v-text-field>
                </v-col>
              </v-row>
            </v-col>
            <v-col cols="8">
              <v-row class="mt-5 mb-5">
                <v-data-table
                  :headers="fte_header"
                  :items="years"
                  sort-by="year"
                  class="elevation-3 mx-auto my-auto"
                  :style="{ width: '500px' }"
                >
                  <template v-slot:top>
                    <v-toolbar flat>
                      <v-toolbar-title>FTE</v-toolbar-title>
                      <v-divider class="mx-4" inset vertical></v-divider>
                      <v-spacer></v-spacer>
                      <v-dialog v-model="dialog" max-width="500px">
                        <template v-slot:activator="{ on, attrs }">
                          <v-btn
                            color="primary"
                            dark
                            class="mb-2"
                            v-bind="attrs"
                            v-on="on"
                          >
                            New Item
                          </v-btn>
                        </template>
                        <v-card>
                          <v-card-title>
                            <span class="text-h5">{{ formTitle }}</span>
                          </v-card-title>

                          <v-card-text>
                            <v-container>
                              <v-row>
                                <v-col cols="12" sm="6" md="4">
                                  <v-text-field
                                    v-model="editedItem.year"
                                    label="Year"
                                  ></v-text-field>
                                </v-col>
                                <v-col cols="12" sm="6" md="4">
                                  <v-text-field
                                    v-model="editedItem.fte"
                                    label="FTE%"
                                  ></v-text-field>
                                </v-col>
                              </v-row>
                            </v-container>
                          </v-card-text>

                          <v-card-actions>
                            <v-spacer></v-spacer>
                            <v-btn color="blue darken-1" text @click="close">
                              Cancel
                            </v-btn>
                            <v-btn color="blue darken-1" text @click="save">
                              Save
                            </v-btn>
                          </v-card-actions>
                        </v-card>
                      </v-dialog>
                      <v-dialog v-model="dialogDelete" max-width="500px">
                        <v-card>
                          <v-card-title class="text-h5">
                            Are you sure you want to delete this item?
                          </v-card-title>
                          <v-card-actions>
                            <v-spacer></v-spacer>
                            <v-btn
                              color="blue darken-1"
                              text
                              @click="closeDelete"
                            >
                              Cancel
                            </v-btn>
                            <v-btn
                              color="blue darken-1"
                              text
                              @click="deleteItemConfirm"
                            >
                              OK
                            </v-btn>
                            <v-spacer></v-spacer>
                          </v-card-actions>
                        </v-card>
                      </v-dialog>
                    </v-toolbar>
                  </template>
                  <template v-slot:[`item.actions`]="{ item }">
                    <v-icon small class="mr-2" @click="editItem(item)">
                      mdi-pencil
                    </v-icon>
                    <v-icon small @click="deleteItem(item)">
                      mdi-delete
                    </v-icon>
                  </template>
                </v-data-table>
              </v-row>
              <v-row>
                <v-col align="right">
                  <v-btn
                    color="primary"
                    elevation="4"
                    outlined
                    x-large
                    @click="rhd = false"
                    class="mr-5"
                    >Close
                  </v-btn>
                  <v-btn
                    color="primary"
                    elevation="4"
                    outlined
                    x-large
                    @click="submit('RHD', $event)"
                    >Submit
                  </v-btn>
                </v-col>
              </v-row>
            </v-col>
          </v-row>
        </v-card>
      </v-row>
    </v-container>
    <template>
      <v-data-table
        :headers="headers"
        :items="researcherFixed_list"
        :search="search"
        :single-expand="true"
        :key="table_key"
        item-key="loop1"
        show-expand
        @click:row="fill_data($event)"
      >
        <template v-slot:top>
          <v-toolbar flat>
            <v-toolbar-title>Staff</v-toolbar-title>
            <v-spacer></v-spacer>
          </v-toolbar>
        </template>
        <template v-slot:expanded-item="{ item }">
          <td></td>
          <td :style="{ padding: '10px' }">
            <tr
              v-for="(ite, index) in item.extra"
              @click="say('Clicked on table: ' + index)"
              :key="index"
            >
              {{
                ite.classification
              }}
            </tr>
          </td>
          <td
            v-for="(ite, index) in item.extra"
            :key="index"
            @click="say('Clicked on table: ' + index)"
          ></td>
          <td
            v-for="(ite, index) in item.extra"
            :key="index"
            @click="say('Clicked on table: ' + index)"
          ></td>
          <td>
            <tr
              v-for="(ite, index) in item.extra"
              :key="index"
              @click="say('Clicked on table: ' + index)"
            >
              {{
                ite.income
              }}
            </tr>
          </td>
          <td>
            <tr
              v-for="(ite, index) in item.extra"
              :key="index"
              @click="say('Clicked on table: ' + index)"
            >
              {{
                ite.inKind
              }}
            </tr>
          </td>
          <td>
            <tr
              v-for="(ite, index) in item.extra"
              :key="index"
              @click="say('Clicked on table: ' + index)"
            >
              {{
                ite.actual
              }}
            </tr>
          </td>
        </template>
      </v-data-table>
    </template>
  </v-app>
</template>

<script>
const axios = require("axios").default;

export default {
  data: () => ({
    search: "",
    headers: [
      {
        text: "Name",
        align: "start",
        filterable: true,
        value: "name",
      },
      { text: "Role", value: "role" },
      { text: "Contract", value: "contract" },
      { text: "Cash Income", value: "cash_income" },
      { text: "In Kind Contribution", value: "inKindPercent" },
      { text: "Actual Cost", value: "actualCost" },
    ],

    dialog: false,
    dialogDelete: false,
    fte_header: [
      {
        text: "Year",
        align: "start",
        sortable: false,
        value: "year",
      },
      { text: "FTE%", value: "fte" },
      { text: "Actions", value: "actions", sortable: false },
    ],
    years: [],
    editedIndex: -1,
    editedItem: {
      year: "",
      fte: 0,
    },
    defaultItem: {
      year: "",
      fte: 0,
    },

    table_key: 0,
    researcher_list: [],
    researcherFixed_list: [],
    title: ["Dr", "Mr", "Mrs", "Ms"],
    contract: ["RHD", "NON_CASUAL", "CASUAL"],
    staff_type: ["ACADEMIC", "PROFESSIONAL"],
    classification: ["A", "RA1", "APA"],
    step: [1, 2, 3, 4, 5],
    salary_cost_rate: [1.0, 123.0],
    pay_code: [1, 2, 3, 4, 5],
    casual: false,
    rhd: false,
    full_time: false,

    title_input: "",
    family_name: "",
    given_name: "",
    role_input: "",
    contract_input: "",

    staff_type_full: "",
    classification_full: "",
    step_full: "",
    start_salary_full: "",
    wage_adj_full: "",
    salary_rate_full: "",
    in_kind_full: "",

    staff_type_casual: "",
    classification_casual: "",
    pay_code_casual: "",
    start_salary_casual: "",
    wage_adj_casual: "",
    salary_rate_casual: "",
    in_kind_casual: "",

    classification_rhd: "",
    annual_salary_rhd: "",
    in_kind_rhd: "",

    contractID: "",

    researchers: [],
  }),

  computed: {
    formTitle() {
      return this.editedIndex === -1 ? "New Item" : "Edit Item";
    },
  },

  watch: {
    dialog(val) {
      val || this.close();
    },
    dialogDelete(val) {
      val || this.closeDelete();
    },
  },

  created() {
    this.initialize();
  },

  methods: {
    say(e) {
      alert(e);
    },
    checkContract(e) {
      if (e == "NON_CASUAL") {
        this.casual = false;
        this.rhd = false;
        this.full_time = true;
      } else if (e == "CASUAL") {
        this.casual = true;
        this.rhd = false;
        this.full_time = false;
      } else if (e == "RHD") {
        this.casual = false;
        this.rhd = true;
        this.full_time = false;
      } else {
        this.casual = false;
        this.rhd = false;
        this.full_time = false;
      }
    },
    update(e) {
      console.log("UPDATED DB WITH " + e.target.value);
    },
    fill_data(e) {
      //set basic details that apply to every contract
      this.initialize();

      this.title_input = e.title;
      this.family_name = e.lastName;
      this.given_name = e.firstName;
      this.role_input = e.role;
      this.contract_input = e.contract;
      this.contractID = e.contractID;
      console.log("?" + e.contractID);

      axios
        .get("http://10.36.241.204:8080/api/researchers/1/" + e.contractID)
        .then((resp) => {
          console.log(resp.data); //use resp.data[0].name for arrays
          this.researcher_list = resp.data;
          var fte_years;
          var obj;
          if (e.contract == "NON_CASUAL") {
            this.checkContract("NON_CASUAL");
            this.staff_type_full = resp.data.staffType;
            this.classification_full = resp.data.classification;
            this.step_full = resp.data.step;
            this.start_salary_full = resp.data.startingSalary;
            this.wage_adj_full = resp.data.wageAdjustment;
            this.salary_rate_full = resp.data.salaryOnCostRate;
            this.in_kind_full = e.inKindPercent;
            this.annualContributions = resp.data.annualContributions;
            console.log(resp.data.annualContributions);
            for (fte_years in resp.data.annualContributions) {
              obj = {
                year: fte_years,
                fte: resp.data.annualContributions[fte_years],
              };
              this.years.push(obj);
            }
          } else if (e.contract == "CASUAL") {
            this.checkContract("CASUAL");
            this.staff_type_casual = resp.data.staffType;
            this.classification_casual = resp.data.classification;
            this.pay_code_casual = resp.data.payCode;
            this.start_salary_casual = resp.data.hourlyRate;
            this.wage_adj_casual = resp.data.wageAdjustment;
            this.salary_rate_casual = resp.data.salaryOnCostRate;
            this.in_kind_casual = e.inKindPercent;
            this.annualContributions = resp.data.annualContributions;
            for (fte_years in resp.data.annualContributions) {
              obj = {
                year: fte_years,
                fte: resp.data.annualContributions[fte_years],
              };
              this.years.push(obj);
            }
          } else if (e.contract == "RHD") {
            this.checkContract("RHD");
            this.classification_rhd = resp.data.classification;
            this.annual_salary_rhd = resp.data.wageExpense;
            this.in_kind_rhd = e.inKindPercent;
            this.annualContributions = resp.data.annualContributions;
            for (fte_years in resp.data.annualContributions) {
              obj = {
                year: fte_years,
                fte: resp.data.annualContributions[fte_years],
              };
              this.years.push(obj);
            }
          }
        });
    },

    load_researcher_list() {
      axios.get("http://10.36.241.204:8080/api/researchers/1").then((resp) => {
        console.log(resp.data); //use resp.data[0].name for arrays
        this.researcher_list = resp.data;
        var obj;
        var done = [];

        for (var i = 0; i < this.researcher_list.length; i++) {
          if (!done.includes(this.researcher_list[i].staffID)) {
            var count = 0;
            var extra2 = [];
            var obj2;
            var first = true;

            for (
              var staffNum = 0;
              staffNum < this.researcher_list.length;
              staffNum++
            ) {
              if (
                this.researcher_list[staffNum].staffID ==
                this.researcher_list[i].staffID
              ) {
                count++;
                if (!first) {
                  obj2 = {
                    classification: this.researcher_list[staffNum].contract,
                    income: "$111",
                    inKind: this.researcher_list[staffNum].inKindPercent,
                    actual: "$" + this.researcher_list[staffNum].actualCost,
                  };
                  extra2.push(obj2);
                }
                first = false;
              }
            }

            obj = {
              title: this.researcher_list[i].title,
              name:
                this.researcher_list[i].firstName +
                " " +
                this.researcher_list[i].lastName +
                " (" +
                (count - 1) +
                ")",
              firstName: this.researcher_list[i].firstName,
              lastName: this.researcher_list[i].lastName,
              staffID: this.researcher_list[i].staffID,
              role: this.researcher_list[i].role,
              contract: this.researcher_list[i].contract,
              contractID: this.researcher_list[i].contractID,
              cash_income: "$" + 0,
              inKindPercent: this.researcher_list[i].inKindPercent,
              actualCost: "$" + this.researcher_list[i].actualCost,
              extra: extra2,
            };
            this.researcherFixed_list.push(obj);
            done.push(this.researcher_list[i].staffID);
          }
        }
      });
    },

    // lastName: this.family_name,
    // firstName: this.given_name,
    // contract: this.contract_input,
    // staffType: this.staff_type_full,
    // classification: this.classification_full,
    // step: this.step_full,
    // startingSalary: this.start_salary_full,
    // wageAdjustment: this.wage_adj_full,
    // salaryonCostRate: this.salary_rate_full,

    submit(contract, e) {
      if (this.contractID == "") {
        console.log("EMPTY");
      }
      if (contract == "NON_CASUAL") {
        console.log(
          "UPDATING RESEARCHER WITH " +
            this.family_name +
            " " +
            this.given_name +
            " " +
            this.role_input +
            " " +
            this.contract_input +
            " " +
            this.staff_type_full +
            " " +
            this.classification_full +
            " " +
            this.step_full +
            " " +
            this.start_salary_full +
            " " +
            this.wage_adj_full +
            " " +
            this.salary_rate_full +
            " " +
            this.in_kind_full
        );
        var annual = {};
        for (var index in this.years) {
          console.log(this.years[index].fte);
          annual[this.years[index].year] = this.years[index].fte;
        }
        axios.put(
          "http://10.36.241.204:8080/api/researchers/1/" + this.contractID,
          {
            role: this.role_input,
            inKindPercent: this.in_kind_full,
            annualContributions: annual,
          }
        );
      } else if (contract == "CASUAL") {
        console.log(
          "UPDATING RESEARCHER WITH " +
            this.family_name +
            " " +
            this.given_name +
            " " +
            this.role_input +
            " " +
            this.contract_input +
            " " +
            this.staff_type_casual +
            " " +
            this.classification_casual +
            " " +
            this.pay_code_casual +
            " " +
            this.start_salary_casual +
            " " +
            this.wage_adj_casual +
            " " +
            this.salary_rate_casual +
            " " +
            this.in_kind_casual
        );
      } else if (contract == "RHD") {
        console.log(
          "UPDATING RESEARCHER WITH STAFF ID:" +
            e.staffID +
            " " +
            this.family_name +
            " " +
            this.given_name +
            " " +
            this.role_input +
            " " +
            this.contract_input +
            " " +
            this.classification_rhd +
            " " +
            this.annual_salary_rhd +
            " " +
            this.in_kind_rhd
        );
      }
    },

    initialize() {
      this.years = [];
    },

    editItem(item) {
      this.editedIndex = this.years.indexOf(item);
      this.editedItem = Object.assign({}, item);
      this.dialog = true;
    },

    deleteItem(item) {
      this.editedIndex = this.years.indexOf(item);
      this.editedItem = Object.assign({}, item);
      this.dialogDelete = true;
    },

    deleteItemConfirm() {
      this.years.splice(this.editedIndex, 1);
      this.closeDelete();
    },

    close() {
      this.dialog = false;
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem);
        this.editedIndex = -1;
      });
    },

    closeDelete() {
      this.dialogDelete = false;
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem);
        this.editedIndex = -1;
      });
    },

    save() {
      if (this.editedIndex > -1) {
        Object.assign(this.years[this.editedIndex], this.editedItem);
      } else {
        this.years.push(this.editedItem);
      }
      this.close();
    },
  },

  mounted() {
    if (this.$store.state.projectIndex == -1) {
      this.$router.push("/");
    }
    this.load_researcher_list();
  },
};
</script>
