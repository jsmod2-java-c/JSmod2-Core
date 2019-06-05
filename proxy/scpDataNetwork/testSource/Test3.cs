namespace scpDataNetwork.testSource
{
    public class Test3
    {
        public int A { get; set; }
        public int B { get; set; }
        
        public Test2 test { get; set; }

        public override string ToString()
        {
            return A + "," + B+test;
        }
    }
}