using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Newtonsoft.Json;
namespace TableServer
{
    public class JsonObject
    {
        public enum Type { auth, data};
        
        public Type type {get;set;}
        public String data { get; set; }
        
        public JsonObject(Type type, String data)
        {
            this.data = data;
            this.type = type;
        }
        
        //Vor working convert in Newtonsoft.Json we have to use the c# construct of getter and setter
        /*public String getDataString() { return data; }
        public Type getType() { return type; }

        public void setType(Type type) { this.type = type;  }
        public void setData(String data) { this.data = data; }*/

    }
}
