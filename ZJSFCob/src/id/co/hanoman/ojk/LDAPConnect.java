package id.co.hanoman.ojk;

import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.naming.Context;
import javax.naming.NameNotFoundException;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

public class LDAPConnect {
	final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();

	public static String bytesToHex(byte[] bytes) {
		char[] hexChars = new char[bytes.length * 2];
		for (int j = 0; j < bytes.length; j++) {
			int v = bytes[j] & 0xFF;
			hexChars[j * 2] = hexArray[v >>> 4];
			hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		}
		return new String(hexChars);
	}

	public static byte[] hexToBytes(String s) {
		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character
					.digit(s.charAt(i + 1), 16));
		}
		return data;
	}

	public static String encrypt(String key, String plain) {
		try {
			Charset utf8 = Charset.forName("UTF8");
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(key.getBytes(utf8));
			byte bb[] = md.digest();
			byte bkey[] = new byte[16];
			System.arraycopy(bb, 0, bkey, 0, bkey.length);

			SecretKey skey = new SecretKeySpec(bkey, "AES");

			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, skey);
			// TODO Base64
			return bytesToHex(cipher.doFinal(plain.getBytes(utf8)));
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e.getMessage(), e);
		} catch (IllegalBlockSizeException e) {
			throw new RuntimeException(e.getMessage(), e);
		} catch (BadPaddingException e) {
			throw new RuntimeException(e.getMessage(), e);
		} catch (NoSuchPaddingException e) {
			throw new RuntimeException(e.getMessage(), e);
		} catch (InvalidKeyException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public static String decrypt(String key, String crypt) {
		try {
			Charset utf8 = Charset.forName("UTF8");
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(key.getBytes(utf8));
			byte bb[] = md.digest();
			byte bkey[] = new byte[16];
			System.arraycopy(bb, 0, bkey, 0, bkey.length);

			SecretKey skey = new SecretKeySpec(bkey, "AES");

			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, skey);

			// TODO Base64
			return new String(cipher.doFinal(hexToBytes(crypt)), utf8);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e.getMessage(), e);
		} catch (IllegalBlockSizeException e) {
			throw new RuntimeException(e.getMessage(), e);
		} catch (BadPaddingException e) {
			throw new RuntimeException(e.getMessage(), e);
		} catch (NoSuchPaddingException e) {
			throw new RuntimeException(e.getMessage(), e);
		} catch (InvalidKeyException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	// public static boolean validateUserIP(MbElement body) {
	// TODO impl
	// return false;
	// }

	public static Set<String> getUserIP(String user) {
		LdapContext ldap = null;
		try {
			// TODO CACHE REQUEST

			Hashtable<String, String> env = new Hashtable<String, String>();
			env.put(Context.INITIAL_CONTEXT_FACTORY,
					"com.sun.jndi.ldap.LdapCtxFactory");
			env.put(Context.PROVIDER_URL, "ldap://192.168.0.136");
			env.put(Context.SECURITY_AUTHENTICATION, "simple");
			env.put(Context.SECURITY_PRINCIPAL, "cn=manager,dc=ojk,dc=com");
			env.put(Context.SECURITY_CREDENTIALS, "aaasss");

			ldap = new InitialLdapContext(env, null);

			SearchControls searchCtls = new SearchControls();
			searchCtls
					.setReturningAttributes(new String[] { "ipNetworkNumber" });
			searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);

			NamingEnumeration<SearchResult> answer = ldap.search("uid=" + user
					+ ",ou=users,dc=ojk,dc=com", "(objectClass=ipNetwork)",
					searchCtls);
			Set<String> result = new HashSet<String>();
			while (answer.hasMoreElements()) {
				SearchResult sr = (SearchResult) answer.next();
				result.add((String) sr.getAttributes().get("ipNetworkNumber")
						.get());
			}
			return result;
		} catch (NameNotFoundException e) {
			return Collections.emptySet();
		} catch (NamingException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			if (ldap != null) {
				try {
					ldap.close();
				} catch (NamingException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		try {
			// String cr = encrypt("DODOL", "DUREN");
			// System.out.println(cr);
			//
			// String pl = decrypt("DODOL", cr);
			// System.out.println(pl);

			System.out.println("GET USER IP: " + getUserIP("dodol"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
