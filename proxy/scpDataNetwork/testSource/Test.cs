namespace scpDataNetwork.testSource
{
    public class Test
    {
        public int a { get; set; }
        public int b { get; set; }
        
        public Test2 test { get; set; }

        public Test()
        {
            test = new Test2();
        }
    }
}