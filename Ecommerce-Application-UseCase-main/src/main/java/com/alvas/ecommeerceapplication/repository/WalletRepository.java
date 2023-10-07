package com.alvas.ecommeerceapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alvas.ecommeerceapplication.entity.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
	Wallet findByWalletIdAndUserUserId(long walletId,long userId);

}
