package Models;

import org.w3c.dom.Node;

public class User {

        private String FirstName;
        public String getFirstName() {
            return FirstName;
        }
        public void setFirstName(String FirstName) {
            this.FirstName = FirstName;
        }


        private String LastName;
        public String getLastName(){
            return LastName;
        }
        public void setLastName(String lastName) {
            this.LastName = lastName;
        }

        private String Address;
        public String getAddress(){
            return Address;
        }
        public void setAddress(String address) {
            this.Address = address;
        }

        private String City;
            public String getCity(){
            return City;
        }
        public void setCity(String city) {
            this.City = city;
        }

        private String Country;
            public String getCountry(){
            return Country;
        }
        public void setCountry(String country) {
            Country = country;
        }

        private String Email;
        public String getEmail(){
            return Email;
        }
        public void setEmail(String email) {
                Email = email;
        }

        private String Password;
        public String getPassword(){
            return Password;
        }
        public void setPassword(String password) {
            Password = password;
        }


}
