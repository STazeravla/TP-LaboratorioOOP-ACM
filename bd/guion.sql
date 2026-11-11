-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema bd_fatal_final
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema bd_fatal_final
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bd_fatal_final` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `bd_fatal_final` ;

-- -----------------------------------------------------
-- Table `bd_fatal_final`.`comuna`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_fatal_final`.`comuna` (
  `Nombre_Sede` VARCHAR(45) NOT NULL,
  `Numero_Comuna` INT NOT NULL,
  `Junta_Responsable` VARCHAR(45) NOT NULL,
  `id_comuna` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id_comuna`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bd_fatal_final`.`persona`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_fatal_final`.`persona` (
  `DNI` VARCHAR(45) NOT NULL,
  `Fecha_Nacimiento` VARCHAR(45) NOT NULL,
  `Situacion_laboral` VARCHAR(45) NULL DEFAULT NULL,
  `Nombre_completo` VARCHAR(45) NULL DEFAULT NULL,
  `idPersona` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`idPersona`),
  UNIQUE INDEX `DNI_UNIQUE` (`DNI` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bd_fatal_final`.`barrio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_fatal_final`.`barrio` (
  `Nombre` VARCHAR(45) NOT NULL,
  `Superficie` INT NOT NULL,
  `Porcentaje_Asfalto` INT NULL DEFAULT NULL,
  `Cantidad_EdificiosPh` INT NULL DEFAULT NULL,
  `Conexion_Agua` BIT(1) NULL DEFAULT NULL,
  `Ley_Integracion` VARCHAR(45) NULL DEFAULT NULL,
  `Consorsio_Activo` BIT(1) NULL DEFAULT NULL,
  `Cantidad_Monoblocks` INT NULL DEFAULT NULL,
  `fk_comuna` INT NOT NULL,
  `fk_Persona` INT NOT NULL,
  `id_barrio` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id_barrio`),
  INDEX `id_persona_idx` (`fk_Persona` ASC) VISIBLE,
  INDEX `id_comuna_idx` (`fk_comuna` ASC) VISIBLE,
  CONSTRAINT `id_comuna`
    FOREIGN KEY (`fk_comuna`)
    REFERENCES `bd_fatal_final`.`comuna` (`id_comuna`),
  CONSTRAINT `id_persona`
    FOREIGN KEY (`fk_Persona`)
    REFERENCES `bd_fatal_final`.`persona` (`idPersona`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bd_fatal_final`.`situacion_laboral`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_fatal_final`.`situacion_laboral` (
  `Desocupado` INT NULL DEFAULT NULL,
  `Ocupado` INT NULL DEFAULT NULL,
  `Subocupado` INT NULL DEFAULT NULL,
  `IdSituacion_laboral` INT NOT NULL,
  `fkpersona` INT NOT NULL,
  PRIMARY KEY (`IdSituacion_laboral`),
  UNIQUE INDEX `IdSituacion_laboral_UNIQUE` (`IdSituacion_laboral` ASC) VISIBLE,
  INDEX `idpersona_idx` (`fkpersona` ASC) VISIBLE,
  CONSTRAINT `idpersona`
    FOREIGN KEY (`fkpersona`)
    REFERENCES `bd_fatal_final`.`persona` (`idPersona`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
