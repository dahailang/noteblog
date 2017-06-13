package com.pursuit.noteblog.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IdGenerator {

    private static Logger log = LoggerFactory.getLogger(IdGenerator.class);
    //id长度
    private int sessionIdLength = 16;
    private Queue<SecureRandom> randoms = new ConcurrentLinkedQueue<SecureRandom>();
    private String secureRandomAlgorithm = "SHA1PRNG";
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    public void setSessionIdLength(int sessionIdLength) {
        this.sessionIdLength = sessionIdLength;
    }
    public String generateSessionId() {
        String prefix = simpleDateFormat.format(new Date());
        
    	byte random[] = new byte[16];
        
        StringBuilder buffer = new StringBuilder();

        int resultLenBytes = 0;

        while (resultLenBytes < sessionIdLength) {
            getRandomBytes(random);
            for (int j = 0; j < random.length && resultLenBytes < sessionIdLength; j++) {
                
            	byte b1 = (byte) ((random[j] & 0xf0) >> 4);
                byte b2 = (byte) (random[j] & 0x0f);
                if (b1 < 10)
                    buffer.append((char) ('0' + b1));
                else
                    buffer.append((char) ('A' + (b1 - 10)));
                if (b2 < 10)
                    buffer.append((char) ('0' + b2));
                else
                    buffer.append((char) ('A' + (b2 - 10)));
                resultLenBytes++;
            }
        }
        return prefix+"$"+buffer.toString();
    }
    
    
    private void getRandomBytes(byte bytes[]) {

        SecureRandom random = randoms.poll();
        if (random == null) {
            random = createSecureRandom();
        }
        random.nextBytes(bytes);
        randoms.add(random);
    }
    private SecureRandom createSecureRandom() {
        SecureRandom result = null;
        long t1 = System.currentTimeMillis();
        if (result == null) {
        	try {
				result = SecureRandom.getInstance(secureRandomAlgorithm);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
        }
        if (result == null) {
            result = new SecureRandom();
        }
        result.nextInt();
        
        long t2=System.currentTimeMillis();
        if( (t2-t1) > 100 ){
        	log.info("sessionIdGenerator.createRandom {} ： {} ", result.getAlgorithm(), Long.valueOf(t2-t1));
        }
        return result;
    }
    public static void main(String[] args) {
    	for (int i = 0; i < 20; i++) {
			System.out.println(new IdGenerator().generateSessionId());
		}
	}
}
