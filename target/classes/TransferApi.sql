

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema TransferApi
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema TransferApi
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `TransferApi` DEFAULT CHARACTER SET utf8 ;
-- -----------------------------------------------------
-- Schema transferapi
-- -----------------------------------------------------
USE `TransferApi` ;

-- -----------------------------------------------------
-- Table `TransferApi`.`Transactions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TransferApi`.`Transactions` (
  `TransactionReference` INT NOT NULL,
  `amount` VARCHAR(45) NOT NULL,
  `accountnr` VARCHAR(45) NOT NULL,
  `TransactionReferenceDateTime` DATETIME NOT NULL,
  UNIQUE INDEX `TransactionReference_UNIQUE` (`TransactionReference` ASC) VISIBLE,
  PRIMARY KEY (`TransactionReference`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TransferApi`.`Balances`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TransferApi`.`Balances` (
  `accountnr` INT NOT NULL,
  `balance` DECIMAL NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`accountnr`),
  UNIQUE INDEX `accountnr_UNIQUE` (`accountnr` ASC) VISIBLE)
ENGINE = InnoDB;

INSERT INTO Balances (name, accountnr,balance) VALUES ('UserA','1234','50000');
INSERT INTO Balances (name, accountnr,balance) VALUES ('UserB','4321','100000');
INSERT INTO Balances (name, accountnr,balance) VALUES ('UserB','5321','150000');



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
