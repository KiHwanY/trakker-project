package com.example.trakker.controller;

import com.example.trakker.model.review.dto.ReviewDTO;
import com.example.trakker.service.item.LocalService;
import com.example.trakker.service.restReview.RestReviewService;
import com.example.trakker.utils.PagingInfoVO;
import com.example.trakker.utils.ResponseResultList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("reviewList")
public class RestReviewController {

    @Autowired
    private RestReviewService reviewService;

    @Autowired
    private LocalService localService;

    @GetMapping
    public String list(Model model,
                       HttpSession session,
                       HttpServletRequest request,
                       @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                       @RequestParam(value = "area",required = false, defaultValue = "0") Integer area,
                       @RequestParam(value = "sort",required = false, defaultValue = "add") String sort,
                       @RequestParam(value = "searchType",required = false, defaultValue = "") String searchType,
                       @RequestParam(value = "keyword",required = false, defaultValue = "") String keyword) {
        Long memNum = (Long) session.getAttribute("mem_num");
        if(memNum==null){
            memNum = 0L;
        }

        PagingInfoVO vo = new PagingInfoVO();
        vo.setPageNum(page);
        vo.setArea(area);
        vo.setSort(sort);
        vo.setStype(searchType);
        vo.setSdata(keyword);

        String urlCheck = request.getServletPath();
        ResponseResultList responseResultList = reviewService.list(vo, memNum, urlCheck);

        model.addAttribute("list", responseResultList.getBody());
        model.addAttribute("page", responseResultList.getMeta().get("pagingInfo"));
        model.addAttribute("select", page);
        model.addAttribute("local", localService.localList());
        model.addAttribute("area", area);
        model.addAttribute("sort", sort);
        model.addAttribute("type", searchType);
        model.addAttribute("keyword",keyword);

        return "reviewList/list";
    }

    @GetMapping("/{review_num}")
    public ModelAndView detail(Model model,
                         HttpSession session,
                         HttpServletRequest request,
                         HttpServletResponse response,
                         @PathVariable("review_num") Long review_num) {

        reviewService.count(review_num, request, response);
        ReviewDTO review = reviewService.detail(review_num);
        Double ratingavg = reviewService.ratingAvg(review_num);
        ModelAndView mav = new ModelAndView();

        mav.setViewName("reviewList/detail");
        mav.addObject("review", review);
        mav.addObject("ratingAvg", ratingavg);

        return mav;

    }
    @GetMapping("/write")
    public String write(Model model) {
        model.addAttribute("reviewDTO", new ReviewDTO());
        return "reviewList/insert";
    }

    @PostMapping("/insert")
    public String insert(@ModelAttribute("reviewDTO") ReviewDTO review) {
        reviewService.insert(review);

        return "redirect:/reviewList";
    }

    @GetMapping("/edit/{review_num}")
    public ModelAndView edit(@PathVariable("review_num") Long reviewNum,
                               HttpServletRequest request,
                               HttpServletResponse response){

        reviewService.count(reviewNum,request,response);
        ReviewDTO review = reviewService.detail(reviewNum);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("reviewList/edit");
        mav.addObject("review",review);

        return mav;
    }

    @PostMapping ("/update/{review_num}")
    public String update(@PathVariable Long review_num, @ModelAttribute("reviewDTO") ReviewDTO review){
        reviewService.update(review_num,review);

        return "redirect:/review/list?num=1";
    }

    @ResponseBody
    @PostMapping("{review_num}")
    public void delete(@PathVariable Long review_num) {
        reviewService.delete(review_num);
    }



}
