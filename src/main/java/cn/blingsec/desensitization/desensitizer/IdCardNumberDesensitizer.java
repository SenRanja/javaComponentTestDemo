package cn.blingsec.desensitization.desensitizer;
import cn.blingsec.desensitization.annotation.IdCardNumberSensitive;


/**
 * 身份证号码脱敏器
 *
 * @author
 */
public class IdCardNumberDesensitizer extends AbstractCharSequenceDesensitizer<String, IdCardNumberSensitive> {

    @Override
    public String desensitize(String target, IdCardNumberSensitive annotation) {
        validateLength(target);

        switch (annotation.desensitizeType()) {
            case TYPE_1:
                return desensitizeType1(target, annotation);
            case TYPE_2:
                return desensitizeType2(target, annotation);
            default:
                throw new IllegalArgumentException("未知的脱敏类型");
        }
//        return required(target, annotation.condition()) ? String.valueOf(desensitize(target, annotation.regexp(), annotation.startOffset(), annotation.endOffset(), annotation.placeholder())) : target;
    }

    private String desensitizeType1(String target, IdCardNumberSensitive annotation) {
        return required(target, annotation.condition()) ? String.valueOf(desensitize(target, annotation.regexp(), annotation.startOffset(), 0, annotation.placeholder())) : target;
    }


    private String desensitizeType2(String target, IdCardNumberSensitive annotation) {
        return required(target, annotation.condition()) ? String.valueOf(desensitize(target, annotation.regexp(), annotation.startOffset(), 4, annotation.placeholder())) : target;
    }


    private void validateLength(String target) {
        int length = target.length();
        if (length!=18) {
            if  (length!=15){
                throw new IllegalArgumentException("数据格式错误，数据脱敏失败!");
            }
        }
    }

}
