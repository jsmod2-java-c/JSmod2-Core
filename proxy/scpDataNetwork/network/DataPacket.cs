/**
 * @author MagicLu550 #(code) jsmod2
 */



namespace scpDataNetwork.network
{
    public abstract class DataPacket : BinaryStream
    {

     

     public DataPacket(int id):base(id)
     {
       
     }
     
        /**
         * 当要发包时，解析为byte数据发出
         */
        public abstract byte[] encode();
        
        
        /**
         * 当收到byte数据包时，解析数据
         */
        
        
       public abstract object decode(byte[] datas);
       
       
    }
}