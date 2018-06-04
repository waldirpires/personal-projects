def files = new File(args[0]).listFiles()
      files.each { file ->
         println file.absolutePath
      }