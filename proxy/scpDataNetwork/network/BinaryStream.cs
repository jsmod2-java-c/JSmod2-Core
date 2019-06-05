using System;
using System.Text;
using Newtonsoft.Json;

namespace scpDataNetwork.network
{
    public class BinaryStream
    {
        public int id { get; }

        public BinaryStream(int id)
        {
            this.id = id;
        }

        public byte[] dataObjectEncode(object o,params string[] things)
        {
            
            string json = JsonConvert.SerializeObject(o);
            StringBuilder builder = new StringBuilder(id+"-"+json);
            //id-{},{"objectName-field":"value"}
            foreach(var str in things)
            {
                builder.Append(","+str);
            }
            byte[] byteArray = System.Text.Encoding.UTF8.GetBytes (builder.ToString());
            string base64 = Convert.ToBase64String(byteArray);
            return System.Text.Encoding.UTF8.GetBytes(base64);
        }

        public object dataObjectDecode(byte[] bytes,Type type)
        {
            string str = System.Text.Encoding.UTF8.GetString(Convert.FromBase64String(System.Text.Encoding.UTF8.GetString(bytes)));
            object o = JsonConvert.DeserializeObject(str.Substring((id+"-").Length),type);
            return o;
        }
    }
}