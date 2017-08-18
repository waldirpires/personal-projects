import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Base64;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

def headerGip = args[0]
println 'header GIP: ' + headerGip.length() + ' chars'
        byte[] genericInterfaceParametersBytes = Base64.getDecoder().decode(headerGip)

            def ba = new ByteArrayInputStream(genericInterfaceParametersBytes)
            def gzip = new GZIPInputStream(ba)

            def reader = new InputStreamReader(gzip)
            def input = new BufferedReader(reader)

            def json
            def sb = new StringBuilder()
            while ((json = input.readLine()) != null)
            {
                sb.append(json)
            }

            println 'Output: '
            println sb.toString()
