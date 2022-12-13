package com.greenart.jpa_test.product.api;

import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greenart.jpa_test.product.entity.ProductInfoVO;
import com.greenart.jpa_test.product.repository.ProductRepository;

@RestController
@RequestMapping("/api/product")
public class ProductAPIController {
    @Autowired ProductRepository prod_repo;
    @PutMapping("") 
    public ResponseEntity<Object> addProductInfo(@RequestBody ProductInfoVO data) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        prod_repo.save(data);
        map.put("status", true);
        map.put("message", "정상적으로 추가되었습니다.");
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }
    
    @GetMapping("/list")
    public ResponseEntity<Object> getProductList(Pageable pageable) {
        // http://localhost:8999/api/product/list?page=0&size=10&sort=seq,desc
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("list", prod_repo.findAll(pageable));
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }
    
    @GetMapping("/detail")
    public ResponseEntity<Object> getProductDetail(@RequestParam Integer prodNo) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("detail", prod_repo.findBySeq(prodNo));
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }
    @DeleteMapping("")
    @Transactional // SQL 실행 시 오류가 발생했다면, 실행 이전의 상태로 되돌림
    public ResponseEntity<Object> deleteProduct(@RequestParam Integer prodNo) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        prod_repo.deleteBySeq(prodNo); // delete from product_info where pi_seq = ""
        map.put("status", true);
        map.put("message", "제품 정보를 삭제했습니다.");
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }
    
    @PatchMapping("/update/{type}")
    @Transactional
    public ResponseEntity<Object> updateProduct(
        @RequestParam Integer prodNo, @PathVariable String type,
        @RequestParam String value
    ) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        ProductInfoVO data = prod_repo.findBySeq(prodNo); // 번호에 해당하는 정보를 가져와서
        if(data == null) { // 가져온 정보가 없다면
            map.put("status", false); // 실패 메시지를 설정하고
            map.put("message", "잘못된 제품 번호 입니다.");
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST); // 실패처리
        } // PathVariable type 값이 name이라면
        else if(type.equals("name"))        data.setName(value);
        else if(type.equals("price"))       data.setPrice(Integer.parseInt(value));
        else if(type.equals("discount"))    data.setDiscount(Double.parseDouble(value));
        else if(type.equals("status"))      data.setStatus(Integer.parseInt(value));
        else if(type.equals("stock"))       data.setStock(Integer.parseInt(value));
        else if(type.equals("regDt")) {      
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss"); 
            try{ data.setRegDt(format.parse(value)); }
            catch(Exception e) { e.printStackTrace(); }
        }
        else {
            map.put("status", false); // 성공 메시지를 설정한다.
            map.put("message", "타입이 잘못되었습니다. [name,price,discount,status,stock,regDt]");
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
        prod_repo.save(data); // 저장(Update)한다
        map.put("status", true); // 성공 메시지를 설정한다.
        map.put("message", "변경되었습니다.");
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }
}
