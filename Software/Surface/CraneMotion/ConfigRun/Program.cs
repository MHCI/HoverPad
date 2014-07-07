using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using CraneMotion;

namespace ConfigRun
{
    class Program : AppConnector
    {
        LinkedList<String> l = new LinkedList<string>();
        Boolean answered = false;
        int durchlaeufe = 5;

        static void Main(string[] args)
        {
            Program p = new Program();
            p.test();

        }

        public void test()
        {
            Console.WriteLine("Start:");
            USBCommunication ino = new USBCommunication(this, "Arduino");
            ino.openCom();
            for (int i = 0; i < 200; i++)
            {
                //ino.sendString("100,1000,0");
                while (!answered)
                {
                }
                /*Console.WriteLine("beginne schlaf");
                System.Threading.Thread.Sleep(5);
                Console.WriteLine("beende schlaf");*/
                answered = false;
            }
            //ino.sendString("100000,100000,100000");
            /*while (!answered)
            {
            }*/

            /*Console.WriteLine("Teste X");
            for (int i = 0; i < durchlaeufe; i++)
            {
                Console.WriteLine("Durchlauf " + i);
                ino.sendString(10000 * -1*i + ",0,0");
                while (!answered)
                {
                }
                answered = false;
            }

            Console.WriteLine("Teste Y");
            for (int i = 0; i < durchlaeufe; i++)
            {
                Console.WriteLine("Durchlauf " + i + ":" + 10000 * (-1 ^ (i + 1)));
                ino.sendString("0,"+10000 * (-1^(i+1)) +",0");
                while (!answered)
                {
                }
                Console.WriteLine("beginne schlaf");
                System.Threading.Thread.Sleep(5000);
                Console.WriteLine("beende schlaf");
                answered = false;
            }
            
            Console.WriteLine("Teste Z");
            for (int i = 0; i < durchlaeufe; i++)
            {
                Console.WriteLine("Durchlauf " + i);
                ino.sendString("0,0," + 100000 * (-1 ^ (i + 1)));
                while (!answered)
                {
                }
                answered = false;
            }*/
            Console.WriteLine("\n\nErgebnisse:");
            foreach (String str in l){
                Console.WriteLine(str);
            }
            ino.closeCom();
            Console.ReadKey();
        }

        public override void receivedData(string answer)
        {
            l.AddFirst(answer);
            answered = true;
        }
    }
}
