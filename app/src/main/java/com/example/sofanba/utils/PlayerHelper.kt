package com.example.sofanba.utils

class PlayerHelper {
    companion object {
        fun getFullName(firstName: String?, lastName: String?): String {
            return if (lastName == null) {
                String.format("%s", firstName)
            } else if (firstName == null) {
                String.format("%s", lastName)
            } else {
                String.format("%s %s", firstName, lastName)
            }
        }
    }
}