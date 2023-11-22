package org.zerock.springex.mapper;


import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.springex.domain.TodoVO;
import org.zerock.springex.dto.PageRequestDTO;

import java.time.LocalDate;
import java.util.List;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class TodoMapperTests {

    @Autowired(required = false)
    private TodoMapper todoMapper;

    @Test
    public void testGetTime(){
        log.info(todoMapper.getTime());
    }

    @Test
    public void testInsert(){
        TodoVO vo = TodoVO.builder()
                .title("스프링 테스트")
                .dueDate(LocalDate.of(2022, 10, 10))
                .writer("1")
                .build();
        todoMapper.insert(vo);

    }
    @Test
    public void testSelectAll(){
        List<TodoVO> voList = todoMapper.selectAll();

        voList.forEach(vo -> log.info(vo));
    }

    @Test
    public void testSelectOne(){
        TodoVO todoVO = todoMapper.selectOne(3L);

        log.info(todoVO);
    }

    @Test
    public void testSelectList(){
        PageRequestDTO dto = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .build();

        List<TodoVO> voList = todoMapper.selectList(dto);

        voList.forEach(vo -> log.info(vo));
    }

    @Test
    public void testSelectSearch(){
        PageRequestDTO requestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .types(new String[]{"t", "w"})
                .keyword("스프링")
//                .finished(false)
                .from(LocalDate.of(2023,11,22))
                .to(LocalDate.of(2023,11,23))
                .build();
        List<TodoVO> voList = todoMapper.selectList(requestDTO);

        voList.forEach(vo -> log.info(vo));
        log.info(todoMapper.getCount(requestDTO));

    }
}
