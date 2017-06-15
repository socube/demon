namespace java com.thriftzk.rpc.demo
service EchoSerivce
{
	string echo(1: string msg);
}