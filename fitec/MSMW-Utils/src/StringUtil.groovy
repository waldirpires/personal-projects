
class StringUtil{

//println generateRandomString(50);


	def static generateRandomString(int length)
	{
		def generator = { String alphabet, int n ->
			new Random().with {
			  (1..n).collect { alphabet[ nextInt( alphabet.length() ) ] }.join()
			}
		  }
		  
		  return generator( (('A'..'Z')+('0'..'9')+('a'..'z')).join(), length )
	}

	def static getArgsMap(args)
	{
		def params = new Properties()
		
		for (int i = 0; i < args.length; i = i + 2)
		{
			def key = args[i].substring(1)
			params.put(key, args[i+1])
		}
		return params
	}
		
}