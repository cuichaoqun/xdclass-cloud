package net.xdclass.pattern;



/**
 * 创建者模式
 *
 * 将一个复杂对象的创建与他的标识分离，使得同样的构建过程可以创建不同的表示
 *
 * 应用场景：
 *
 * 1.需要生成的对象具有复杂的内部结构；
 *
 * 2.需要生成的对象内部属性本身相互依赖；
 *
 * 3.与不可变对象配合使用。
 *
 * 优点：
 *
 * 1.建造者独立，易扩展；
 *
 * 2.便于控制细节风险。
 *
 * spring源码中的使用：
 *
 * 1.org.springframework.web.servlet.mvc.method.RequestMappingInfo;
 *
 * 2.org.springframework.beans.factory.support.BeanDefinitionBuilder;
 *
 * */
public class BuilderTest {

    public static void main(String[] args) {
        ProductBulider productBulider = new DefaultCreateBuilderProduct();
        Director director = new Director(productBulider);
        Product product = director.makeProduct("xxx", "1", "2", "3");
        System.out.println(product.toString());
    }
}

interface ProductBulider{
    void buliderProductName(String productName);
    void buliderPart1(String part1);
    void buliderPart2(String part2);
    void buliderPart3(String part3);
    Product bulid();
}

class Director{
    private ProductBulider productBulider;

    public Director(ProductBulider productBulider){
        this.productBulider = productBulider;
    }

    public Product makeProduct(String productName, String part1, String part2, String part3){
        productBulider.buliderProductName(productName);
        productBulider.buliderPart1(part1);
        productBulider.buliderPart2(part2);
        productBulider.buliderPart3(part3);
        Product product = productBulider.bulid();
        return product;
    }
}

class DefaultCreateBuilderProduct implements ProductBulider{
    private String productName;
    private String part1;
    private String part2;
    private String part3;

    @Override
    public void buliderProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public void buliderPart1(String part1) {
        this.part1 = part1;
    }

    @Override
    public void buliderPart2(String part2) {
        this.part2 = part2;
    }

    @Override
    public void buliderPart3(String part3) {
        this.part3 = part3;
    }

    @Override
    public Product bulid() {
        return new Product( this.productName,  this.part1,  this.part2,  this.part3);
    }
}

class Product{
    private String productName;
    private String part1;
    private String part2;
    private String part3;

    public Product(){

    }

    public Product(String productName, String part1, String part2, String part3) {
        this.productName = productName;
        this.part1 = part1;
        this.part2 = part2;
        this.part3 = part3;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPart1() {
        return part1;
    }

    public void setPart1(String part1) {
        this.part1 = part1;
    }

    public String getPart2() {
        return part2;
    }

    public void setPart2(String part2) {
        this.part2 = part2;
    }

    public String getPart3() {
        return part3;
    }

    public void setPart3(String part3) {
        this.part3 = part3;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                ", part1='" + part1 + '\'' +
                ", part2='" + part2 + '\'' +
                ", part3='" + part3 + '\'' +
                '}';
    }
}