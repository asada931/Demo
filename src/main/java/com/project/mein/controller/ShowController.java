package com.project.mein.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.mein.entity.Languages;
import com.project.mein.entity.Repository;
import com.project.mein.entity.User;
import com.project.mein.service.UserService;

@Controller
public class ShowController {
	@Autowired
	private UserService userService;
	private static final Logger logger = LoggerFactory
			.getLogger(ShowController.class);

	@RequestMapping(value = "", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public String[] home() throws Exception {
		logger.info("Instructions page");
		String[] instructions = {
				"work flow and apis",
				"/api/import/{username}",
				"imports users from github.",
				"saves or updates data in mysql from users,repositories and languages of repositories",
				"/api/show/users",
				"fetches all users from user table",
				"/api/show/{id}/repos",
				"fetches repositories based on user id",
				"/api/show/{id}/lang",
				"fetches languages of repository which belonged to a particular user",
				"show languages used and percentage of contribution in the particular repository",
				"/api/show/{username}",
				"shows languages used and percentage of contribution in all therepositories" };
		return instructions;
	}

	@RequestMapping(value = "/api/show/users", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public ResponseEntity<?> showUsers() throws Exception {
		logger.info("calling:/api/Show/users");
		logger.debug("showUsers() is executed", "arslan");

		List<User> list = userService.getAllUser();
		if (list != null && !list.isEmpty()) {

		} else {
			return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/show/{id}/repos", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public ResponseEntity<?> showUserRepos(@PathVariable("id") Integer id)
			throws Exception {
		logger.info("calling:/api/Show/{id}/repos");
		logger.debug("showUserRepos() is executed value{id:" + id + "}",
				"arslan");

		List<Repository> list = userService.getRepositoryByUserId(id);
		if (list != null && !list.isEmpty()) {
		} else {
			return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Repository>>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/show/{id}/lang", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public ResponseEntity<?> showReposLang(@PathVariable("id") Integer id)
			throws Exception {
		logger.info("calling:/api/Show/{id}/lang");
		logger.debug("showRepoLang() is executed", "arslan");

		List<Languages> list = userService.getLanguageByRepoId(id);
		System.out.println(list != null);
		System.out.println(!list.isEmpty());
		if (list != null && !list.isEmpty()) {
			double total = 0;
			for (Languages languages : list) {
				total = total + languages.getNumber();
				System.out.println(total);
			}
			for (Languages languages : list) {
				// System.out.println(languages.getNumber());
				// System.out.println(total);
				String sum = new DecimalFormat("##.####").format(languages
						.getNumber() / total * 100);
				languages.setNumber(Double.parseDouble(sum));
			}
		} else {
			System.out.println("resource not found lang");
			logger.debug("resource not found lang");
			return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Languages>>(list, HttpStatus.OK);

	}

	@RequestMapping(value = "/api/show/{username}", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public ResponseEntity<?> showUserLangList(
			@PathVariable("username") String username) throws Exception {
		logger.info("calling:/api/Show/{username}");
		logger.debug(
				"showUsers() is executed value{username:" + username + "}",
				"arslan");

		List<Languages> list = userService.getLanguageByUsername(username);
		List<Languages> list2 = new ArrayList<Languages>();
		Languages lang = new Languages();
		Double total = 0.0;
		Double number = 0.0;
		// if user not exists
		if (list != null && !list.isEmpty()) {
			// calculation total number of files
			for (Languages languages : list) {
				total = total + languages.getNumber();
			}
			// System.out.println(total);
			// nested loop for combining same languages and adding the files
			for (Languages languages : list) {
				number = 0.0;
				// int counter = 0;
				for (Languages languagess : list) {
					if (languagess.getName().equals(languages.getName())) {
						// saving updated info in lang
						lang = languagess;
						number = number + languagess.getNumber();
						lang.setNumber(number);
					}

				}

				// no duplicate values
				// counter for insuring only one language is inserted if and
				// only
				// if that language doesn't exist in the list2
				int count = 0;
				// for avoiding null point exception
				if (list2 != null) {
					for (Languages languag : list2) {
						if (languag.getName().equals(lang.getName())) {
							// if the language exists counter is set to 1
							count = 1;
						}
					}
				}
				// adds only in two conditions 1) list2 is empty. 2) if the
				// language doesn't exist in the list
				if (count == 0) {
					// for insuring their are not faulty values
					Languages languages2 = new Languages();
					languages2.setName(lang.getName());
					languages2.setNumber(lang.getNumber());
					// System.out.println(languages2.getName());
					// System.out.println(languages2.getNumber());
					String sum = new DecimalFormat("##.####").format(languages2
							.getNumber() / total * 100);
					languages2.setNumber(Double.parseDouble(sum));
					System.out.println(sum);
					list2.add(languages2);
				}
				lang = new Languages();
			}
		} else {
			return new ResponseEntity<>("",HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Languages>>(list2, HttpStatus.OK);
	}

	@RequestMapping(value = "/handle/404", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public ResponseEntity<Object> handle404() {
		logger.info("Handling 404");
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
