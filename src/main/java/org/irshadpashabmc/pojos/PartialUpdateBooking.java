package org.irshadpashabmc.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PartialUpdateBooking {

        @SerializedName("firstname")
        @Expose
        private String firstname;
        @SerializedName("lastname")
        @Expose
        private String lastname;

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

    }


