package cm.genie6.risehope.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cm.genie6.risehope.controller.AccountController;
import cm.genie6.risehope.controller.DefaultController;
import cm.genie6.risehope.model.Account;
import cm.genie6.risehope.model.Mail;
import cm.genie6.risehope.persistence.AccountRepository;
import cm.genie6.risehope.persistence.MailRepository;

@Service
public class AccountService implements UserDetailsService {

	private AccountRepository accountRepository;
	private MailRepository mailRepository;

	@Autowired
	public AccountService(AccountRepository accountRepository, MailRepository mailRepository) {
		super();
		this.accountRepository = accountRepository;
		this.mailRepository = mailRepository;
	}

	public List<Account> getAllAccount() {
		return accountRepository.findAll();
	}

	public List<Mail> getAllMailOfAccount() {
		return mailRepository.findAll();
	}

	public List<Account> getAllOrphanage() {
		return accountRepository.findByStatus("ORPHANAGE");
	}

	public List<Account> getAllSponsor() {
		return accountRepository.findByStatus("SPONSOR");
	}

	public List<Account> searching(String element) {
		try {
			int phone = Integer.parseInt(element);
			return Arrays.asList(accountRepository.findByCphone(phone));
		} catch (NumberFormatException e) {
			return accountRepository.findPeopleDistinctByPlastnameOrPfirstnameContaining(element, element);
		}

	}

	public List<Account> searchOrphanage(String centerName, String city) {
		String unknow = "unknown";
		if (city.equals(unknow)) {
			return accountRepository.findPeopleDistinctByCnameContaining(centerName);
		} else {
			return accountRepository.findPeopleDistinctByCcityContaining(city);
		}
	}

	public void addAccount(Account account) {
		accountRepository.save(account);
	}

	public void uploadGallery(MultipartFile[] files, String username) throws IOException {
		Account user = getByUsername(Integer.parseInt(username));
		int id = user.getIdaccount();
		StringBuilder filesNames = new StringBuilder();
		for (MultipartFile file : files) {
			Path fileNameAndPath = Paths.get(DefaultController.LOADGALLERY.concat(id + ""), file.getOriginalFilename());
			filesNames.append(file.getOriginalFilename());
			Files.write(fileNameAndPath, file.getBytes());
		}

	}

	public void completeAccountInfos(MultipartFile[] cfiles, MultipartFile[] pfiles, Account account)
			throws IOException {
		account = accountRepository.save(account);
		int id = account.getIdaccount();
		for (int i = 0; i < 1; i++) {
			Path fileNameAndPath = Paths.get(AccountController.PROFILECENTER, id + "");
			Files.write(fileNameAndPath, cfiles[i].getBytes());
		}
		for (int i = 0; i < 1; i++) {
			Path fileNameAndPath = Paths.get(AccountController.PROFILEDIRECTORY, id + "");
			Files.write(fileNameAndPath, pfiles[i].getBytes());
		}
	}

	public void saveImageProfile(MultipartFile[] pfiles, Integer idAccount) throws IOException {
		for (int i = 0; i < 1; i++) {
			Path fileNameAndPath = Paths.get(AccountController.PROFILEDIRECTORY, idAccount + "");
			Files.write(fileNameAndPath, pfiles[i].getBytes());
		}
	}

	public void saveBackgroundImage(MultipartFile[] cfiles, Integer idAccount) throws IOException {
		for (int i = 0; i < 1; i++) {
			Path fileNameAndPath = Paths.get(AccountController.PROFILECENTER, idAccount + "");
			Files.write(fileNameAndPath, cfiles[i].getBytes());
		}
	}

	public void updateAccount(Account account) {
		accountRepository.save(account);
	}

	public Account getAccountById(Integer id) {
		return accountRepository.findById(id).get();
	}

	public Account getByUsername(int phone) {
		return accountRepository.findByCphone(phone);

	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account user = getByUsername(Integer.parseInt(username));
		GrantedAuthority authority = new SimpleGrantedAuthority(user.getStatus());
		return new org.springframework.security.core.userdetails.User(username, user.getCpassword(),
				Arrays.asList(authority));
	}

}
