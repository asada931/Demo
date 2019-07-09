package com.project.mein.controller.test;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import com.project.mein.controller.ImportController;
import com.project.mein.entity.Languages;
import com.project.mein.entity.Repository;
import com.project.mein.entity.User;
import com.project.mein.service.UserService;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:**/WEB-INF/applicationContext.xml")
public class ImportControllerTest {

	private MockMvc mockMvc;

	@Mock
	private RestTemplate restTemplate;
	@Mock
	private UserService userService;

	@InjectMocks
	private ImportController importController;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(importController).build();
	}

	@Test
	public void testImportUser() throws Exception {
		User user = new User(null, "arslanyasinwattoo", "arslan", "germany",
				"none", null,
				"swalla swallaaaaaaaaaaaaaaaaaaaaaaaasdasdaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		ResponseEntity<String> responseEntity = new ResponseEntity<String>(
				"{\"name\":\"arslan\",\"location\":\"germany\",\"company\":\"none\",\"email\":\"null\",\"bio\":\"swalla swallaaaaaaaaaaaaaaaaaaaaaaaasdasdaaaaaaaaaaaaaaaaaaaaaaaaaaa\",\"public_repos\":1}",
				HttpStatus.ACCEPTED);
		when(
				restTemplate.exchange(Matchers.anyString(),
						Matchers.any(HttpMethod.class),
						Matchers.<HttpEntity<?>> any(),
						Matchers.<Class<String>> any())).thenReturn(
				responseEntity);
		User user2 = null;
		when(userService.getUsersByName(user)).thenReturn(user2);

		doNothing().doThrow(new IllegalArgumentException()).when(userService)
				.addUser(user);
		// does nothing the first time:
		// userService.addUser(user);
		// throws IllegalArgumentException the next time:
		// userService.addUser(user);
		user.setUserId(1);

		when(userService.getUsersByName(user)).thenReturn(user);

		Repository repository = new Repository(null, "projects",
				"arslanyasin/projects", "nothing much", user);
		ResponseEntity<String> responseEntityRepo = new ResponseEntity<String>(
				"{ \"name\": \"project\",\"url\":\"arslanyasin/projects\",\"description\":\"nothing much\"}",
				HttpStatus.ACCEPTED);
		when(
				restTemplate.exchange(Matchers.anyString(),
						Matchers.any(HttpMethod.class),
						Matchers.<HttpEntity<?>> any(),
						Matchers.<Class<String>> any())).thenReturn(
				responseEntityRepo);

		when(userService.getRepositoryByUrl(repository)).thenReturn(repository);

		doNothing().doThrow(new IllegalArgumentException()).when(userService)
				.addRepository(repository);
		// does nothing the first time:
		// userService.addRepository(repository);
		// throws IllegalArgumentException the next time:
		// userService.addRepository(repository);

		Languages languages = new Languages(null, "HTML", 15.0);
		ResponseEntity<String> responseEntityLang = new ResponseEntity<String>(
				"{\"HTML\",\"15\"}", HttpStatus.ACCEPTED);

		repository.setRepositoryId(1);
		when(userService.getRepositoryByUrl(repository)).thenReturn(repository);
		when(
				restTemplate.exchange(Matchers.anyString(),
						Matchers.any(HttpMethod.class),
						Matchers.<HttpEntity<?>> any(),
						Matchers.<Class<String>> any())).thenReturn(
				responseEntityLang);
		List<Languages> list = new ArrayList<Languages>();
		list.add(languages);
		when(userService.getLanguageByRepoId(languages)).thenReturn(list);

		doNothing().doThrow(new IllegalArgumentException()).when(userService)
				.addLanguages(languages);

		verifyNoMoreInteractions(userService);

	}

}
