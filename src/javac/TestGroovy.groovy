package javac

// TestGroovy.groovy 定义testC方法，传入3个参数，返回处理后的数据
def testC(int numA, int numB, int numC) {
    "传入参数：" + numA + numB + numC + "计算之和为：" + (numA + numB + numC)
    // groovy会默认返回最后一行的值
}