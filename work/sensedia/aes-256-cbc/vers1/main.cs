using System;
using System.IO;
using System.Security.Cryptography;
using System.Text;

class MainClass {

  public static void Main (string[] args) {
   
    var password =  "1cc772a6-0c6d-4b33-a2d6-ffc96d6a109a";

    byte[] passwordBytes = Encoding.UTF8.GetBytes(password);

    // for (int i = 0; i < passwordBytes.Length; i++){
    //   Console.Write((int) passwordBytes[i] + " ");
    // }
    // Console.WriteLine();

    Console.Write("Hash Bytes: ");
    passwordBytes = SHA256.Create().ComputeHash(passwordBytes);
    for (int i = 0; i < passwordBytes.Length; i++){
      Console.Write((int) passwordBytes[i] + " ");
    }

    // for (int i = 0; i < passwordBytes.Length; i++){
    //   Console.WriteLine((byte) passwordBytes[i] + " - " + Convert.ToString(passwordBytes[i], 2));
    // }
    Console.WriteLine();

    Console.WriteLine("Hash UTF-8: " + Encoding.UTF8.GetString(passwordBytes));
    Console.WriteLine("Hash HEX: " + ByteArrayToString(passwordBytes));
    string base64Password = Convert.ToBase64String(passwordBytes);
    Console.WriteLine("Hash Base64: " + base64Password);
    Console.WriteLine();

    byte[] saltBytes = { 1, 2, 3, 5, 8, 13, 21, 34 };
    byte[]iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

    var text = "12345678901";

    Console.WriteLine("Plain text: " + text);

    // cpf
    Console.WriteLine("Encrypted: " + encrypt(text, base64Password, saltBytes));
    // egajfWvDWq7TEKQbAJm0cw==
    // egajfWvDWq7TEKQbAJm0cw==
    Console.WriteLine("");
    text = "Pedido 1";
    Console.WriteLine("Plain text: " + text);
    Console.WriteLine("Encrypted: " + encrypt(text, base64Password, saltBytes));

    Console.WriteLine("");
    text = "10/08/1979";
    Console.WriteLine("Plain text: " + text);
    Console.WriteLine("Encrypted: " + encrypt(text, base64Password, saltBytes));

  }

public static String encrypt(string text, string passwordBytes, byte[] saltBytes){
      var bytesToBeEncrypted = Encoding.UTF8.GetBytes(text);
    var encryptedBytes = new byte[0];

  using (var ms = new MemoryStream())

            {

                using (var AES = new RijndaelManaged())

                {

                    AES.KeySize = 256;

                    AES.BlockSize = 128;

                    

                    var key = new Rfc2898DeriveBytes(passwordBytes, saltBytes, 1000);

                    AES.Key = key.GetBytes(AES.KeySize / 8);

                    AES.IV = key.GetBytes(AES.BlockSize / 8);

                    AES.Mode = CipherMode.CBC;

                    using (var cs = new CryptoStream(ms, AES.CreateEncryptor(), CryptoStreamMode.Write))

                    {

                        cs.Write(bytesToBeEncrypted, 0, bytesToBeEncrypted.Length);

                        cs.Close();

                    }

                    encryptedBytes = ms.ToArray();
                    //Console.WriteLine(Convert.ToBase64String(encryptedBytes));

                }

            }
          return Convert.ToBase64String(encryptedBytes);
}

public static string Encrypt2(string plainText, byte[] PasswordHash, byte[] SaltKey, byte[] VIKey)
		{
			byte[] plainTextBytes = Encoding.UTF8.GetBytes(plainText);

			byte[] keyBytes = new Rfc2898DeriveBytes(PasswordHash, SaltKey, 1000).GetBytes(256 / 8);
			var symmetricKey = new RijndaelManaged() { Mode = CipherMode.CBC, Padding = PaddingMode.Zeros };
			var encryptor = symmetricKey.CreateEncryptor(keyBytes, VIKey);
			
			byte[] cipherTextBytes;

			using (var memoryStream = new MemoryStream())
			{
				using (var cryptoStream = new CryptoStream(memoryStream, encryptor, CryptoStreamMode.Write))
				{
					cryptoStream.Write(plainTextBytes, 0, plainTextBytes.Length);
					cryptoStream.FlushFinalBlock();
					cipherTextBytes = memoryStream.ToArray();
					cryptoStream.Close();
				}
				memoryStream.Close();
			}
			return Convert.ToBase64String(cipherTextBytes);
		}

public static string Decrypt(string encryptedText, byte[] PasswordHash, byte[] SaltKey, byte[] VIKey)
		{
			byte[] cipherTextBytes = Convert.FromBase64String(encryptedText);
			byte[] keyBytes = new Rfc2898DeriveBytes(PasswordHash, SaltKey, 1000).GetBytes(256 / 8);
			var symmetricKey = new RijndaelManaged() { Mode = CipherMode.CBC, Padding = PaddingMode.None };

			var decryptor = symmetricKey.CreateDecryptor(keyBytes, VIKey);
			var memoryStream = new MemoryStream(cipherTextBytes);
			var cryptoStream = new CryptoStream(memoryStream, decryptor, CryptoStreamMode.Read);
			byte[] plainTextBytes = new byte[cipherTextBytes.Length];

			int decryptedByteCount = cryptoStream.Read(plainTextBytes, 0, plainTextBytes.Length);
			memoryStream.Close();
			cryptoStream.Close();
			return Encoding.UTF8.GetString(plainTextBytes, 0, decryptedByteCount).TrimEnd("\0".ToCharArray());
		}

public static string ByteArrayToString(byte[] ba)
{
  return BitConverter.ToString(ba).Replace("-","");
}          
}