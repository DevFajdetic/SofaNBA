package com.example.sofanba.utils

import com.example.sofanba.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


class TeamsHelper {
    companion object {

        fun convertDate(date: String): String? {
            val inputFormatter: DateTimeFormatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH)
            val outputFormatter: DateTimeFormatter =
                DateTimeFormatter.ofPattern("EEEE, dd MMM yyyy", Locale.ENGLISH)
            val date: LocalDate = LocalDate.parse(date, inputFormatter)
            return outputFormatter.format(date)
        }
        fun getTeamImageById(id: Int?): Int {
            return when (id) {
                1 -> R.drawable.ic_atlanta_hawks_logo
                2 -> R.drawable.ic_boston_celtics_logo
                3 -> R.drawable.ic_brooklyn_nets_logo
                4 -> R.drawable.ic_charlotte_hornets_logo
                5 -> R.drawable.ic_chicago_bulls_logo
                6 -> R.drawable.ic_cleveland_cavaliers_logo
                7 -> R.drawable.ic_dallas_mavericks_logo
                8 -> R.drawable.ic_denver_nuggets_logo
                9 -> R.drawable.ic_detroit_pistons_logo
                10 -> R.drawable.ic_golden_state_warriors_logo
                11 -> R.drawable.ic_huston_rockets_logo
                12 -> R.drawable.ic_indiana_pacers_logo
                13 -> R.drawable.ic_los_angeles_clippers_logo
                14 -> R.drawable.ic_los_angeles_lakers_logo
                15 -> R.drawable.ic_memphis_grizzlies_logo
                16 -> R.drawable.ic_miami_heat_logo
                17 -> R.drawable.ic_milwuakee_bucks_logo
                18 -> R.drawable.ic_minnesota_timberwolves_logo
                19 -> R.drawable.ic_new_orleans_pelicans_logo
                20 -> R.drawable.ic_ny_knicks_logo
                21 -> R.drawable.ic_oklahoma_city_thunder_logo
                22 -> R.drawable.ic_orlando_magic_logo
                23 -> R.drawable.ic_philadelphia_76_ers_logo
                24 -> R.drawable.ic_phoenix_suns_logo
                25 -> R.drawable.ic_portland_trailblazers_logo
                26 -> R.drawable.ic_sacramento_kings_logo
                27 -> R.drawable.ic_san_antonio_spurs_logo
                28 -> R.drawable.ic_toronto_raptors_logo
                29 -> R.drawable.ic_utah_jazz_logo
                30 -> R.drawable.ic_washington_wizards_logo
                else -> {
                    R.drawable.ic_player_1_small
                }
            }
        }

        fun getTeamColorById(id: Int?): Int {
            return when (id) {
                1 -> R.color.team_hawks_primary
                2 -> R.color.team_celtics_primary
                3 -> R.color.team_nets_primary
                4 -> R.color.team_hornets_primary
                5 -> R.color.team_bulls_primary
                6 -> R.color.team_cavaliers_primary
                7 -> R.color.team_mavericks_primary
                8 -> R.color.team_nuggets_primary
                9 -> R.color.team_pistons_primary
                10 -> R.color.team_warriors_primary
                11 -> R.color.team_rockets_primary
                12 -> R.color.team_pacers_primary
                13 -> R.color.team_clippers_primary
                14 -> R.color.team_lakers_primary
                15 -> R.color.team_grizzlies_primary
                16 -> R.color.team_heat_primary
                17 -> R.color.team_bucks_primary
                18 -> R.color.team_timberwolves_primary
                19 -> R.color.team_pelicans_primary
                20 -> R.color.team_knicks_primary
                21 -> R.color.team_thunder_primary
                22 -> R.color.team_magic_primary
                23 -> R.color.team_76_ers_primary
                24 -> R.color.team_suns_primary
                25 -> R.color.team_blazers_primary
                26 -> R.color.team_kings_primary
                27 -> R.color.team_spurs_primary
                28 -> R.color.team_raptors_primary
                29 -> R.color.team_jazz_primary
                30 -> R.color.team_wizards_primary
                else -> {
                    R.color.color_primary
                }
            }
        }

        fun getStadiumLocatiomById(id: Int?): String {
            return when (id) {
                1 -> "33.757222, -84.396389"
                2 -> "42.366303, -71.062228"
                3 -> "40.68265, -73.974689"
                4 -> "35.225, -80.839167"
                5 -> "41.880556, -87.674167"
                6 -> "41.496389, -81.688056"
                7 -> "32.790556, -96.810278"
                8 -> "39.748611, -105.0075"
                9 -> "42.696944, -83.245556"
                10 -> "37.768056, -122.3875"
                11 -> "29.750833, -95.362222"
                12 -> "39.763889, -86.155556"
                13 -> "34.043056, -118.267222"
                14 -> "34.043056, -118.267222"
                15 -> "35.138333, -90.050556"
                16 -> "25.781389, -80.188056"
                17 -> "43.043611, -87.916944"
                18 -> "44.979444, -93.276111"
                19 -> "29.948889, -90.081944"
                20 -> "40.750556, -73.993611"
                21 -> "35.463333, -97.515"
                22 -> "28.539167, -81.383611"
                23 -> "39.901111, -75.171944"
                24 -> "33.445833, -112.071389"
                25 -> "45.531667, -122.666667"
                26 -> "38.649167, -121.518056"
                27 -> "29.426944, -98.4375"
                28 -> "43.643333, -79.379167"
                29 -> "40.768333, -111.901111"
                30 -> "38.898056, -77.020833"
                else -> {
                    "0.00, 0.00"
                }
            }
        }
    }
}
