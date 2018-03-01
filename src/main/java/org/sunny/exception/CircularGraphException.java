
package org.sunny.exception;
/**
 * 有环图
 */
public class CircularGraphException extends Exception {
    public CircularGraphException(String str){
        super(str);
    }
}
